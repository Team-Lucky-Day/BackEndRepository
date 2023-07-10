package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.dto.LoginDto;
import LD_Caffe.ld_caffe.dto.MenuDto;
import LD_Caffe.ld_caffe.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // 서버에서 CORS 관리할때 사용
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    @Value("${ADMIN.PW}")
    private String ADMIN_PW;


    private boolean amIAdmin(Authentication authentication){  // ADMIN 계정 확인 메서드
        return authentication.getName().equals("ADMIN"); // ADMIN 이 맞다면 TRUE 아니면 FALSE
    }

    @PostMapping("/users")
    public ArrayList<String> AlluserInfo(Authentication authentication){

        return adminService.getUserNames();
    }

    @DeleteMapping("/users/delete/{name}")  // 유저 삭제 메서드
    public ResponseEntity<String> deleteUserInfo(@PathVariable String name,Authentication authentication){

        if (!amIAdmin(authentication)) { // ADMIN 이 아니라면 여기서 리턴문으로 빠져나감
            return ResponseEntity.status(403).body("유효한 접근이 아닙니다.");
        }

        if (adminService.deleteUser(name)){
            System.out.println("데이터 찾음 // 데이터 삭제 완료");
            return ResponseEntity.ok("데이터 삭제 완료");
        }else {
            System.out.println("데이터 없음 // 데이터 삭제 실패");
            return ResponseEntity.notFound().build();
        }
    }


    // 메뉴 데이터베이스 저장 메서드
    @PostMapping("/menu")
    public ResponseEntity<String> addMenu(@RequestParam("image") MultipartFile file,
                                          @RequestParam("category") String category,
                                          @RequestParam("name") String name,
                                          @RequestParam("content") String content,
                                          @RequestParam("price") Integer price,
                                          Authentication authentication){

        if (!amIAdmin(authentication)) {  // 어드민이 아니라면 여기서 리턴문으로 블락
            return ResponseEntity.status(403).body("유효한 접근이 아닙니다.");
        }

        System.out.println("<< 추가할 메뉴 >>");
        System.out.println("Category : " +category);
        System.out.println("Menu Name : " + name);
        System.out.println("Menu Content : " + content);
        System.out.println("Menu Price : " + price);
        System.out.println("File Name : " + file.getOriginalFilename());

        try {
            // 파일 이름 생성
            String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(file.getOriginalFilename());

            // 이미지 파일 저장 경로
            String saveDir = "/Users/jujaeyoung/desktop/images/";
            Path filePath = Paths.get(saveDir, fileName);

            // 이미지 파일 저장
            File saveFile = filePath.toFile();
            file.transferTo(saveFile);

            //MenuDto 설정
            MenuDto menuDto = new MenuDto();
            menuDto.setCategory(category);
            menuDto.setName(name);
            menuDto.setContent(content);
            menuDto.setPrice(price);
            menuDto.setImagePath(saveDir + fileName);

            //DB저장
            adminService.addMenu(menuDto);

            return ResponseEntity.ok("메뉴/메뉴이미지 추가 성공");

        }catch (Exception error) { // 데이터베이스에 저장 실패 했을 때
            System.err.println("데이터베이스 저장 실패" + error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("데이터베이스 저장 실패");
        }
    }

    @GetMapping("/menuList")  // 메뉴 리스트 메서드
    public ResponseEntity<List<MenuDto>> MenuList() throws IOException {

        List<MenuDto> menuInfo = adminService.getAllMenuInfo();

        for (MenuDto menu : menuInfo){
            System.out.println(menu.getImageBytes());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(menuInfo, headers, HttpStatus.OK);
    }

    @DeleteMapping("/menu/delete/{menuName}")   // 메뉴삭제 메서드
    public ResponseEntity<String> deleteMenuInfo(@PathVariable String menuName,Authentication authentication){
        if (!amIAdmin(authentication)) { // ADMIN 이 아니라면 여기서 블락
            return ResponseEntity.status(403).body("유효한 접근이 아닙니다.");
        }
        System.out.println(menuName);
        try{
            if (adminService.deleteMenu(menuName)){
                System.out.println(" 메뉴 삭제 완료");
                return ResponseEntity.ok("데이터 삭제 완료");
            }else {
                System.out.println("데이터 없음 // 데이터 삭제 실패");
                return ResponseEntity.notFound().build();
            }

        }catch(Exception error){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("메뉴 삭제 실패");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestBody LoginDto loginDto){ // 어드민 로그인, 토큰발행 메서드
        System.out.println("ADMIN_PW = " + ADMIN_PW);
        System.out.println("어드민 비밀번호 시도 = " + loginDto.getU_pw());
        if (loginDto.getU_pw().equals(ADMIN_PW)) { // 유저가 입력한 비밀번호가 어드민 비밀번호가 맞는경우
            LoginDto admin = new LoginDto();
            admin.setU_id("ADMIN");
            admin.setU_pw(ADMIN_PW);
            String token = adminService.createAdminToken(admin);  // ADMIN 이라는 ID 값을 가진 토큰 발행
            if(token.equals("0")){  // 로그인할때 쓰던 코드 복붙
                return ResponseEntity.badRequest().build();
            }else{
                System.out.println("ADMIN_token = " + token);
                return ResponseEntity.ok().body(token);
            }
        }else{
            return ResponseEntity.badRequest().body("올바르지 않은 비밀번호입니다.");
        }

    }

    @PutMapping("/menu/update/{menuName}")
    public ResponseEntity<?> updateMenuInfo(@PathVariable String menuName,
                                            @RequestParam("editName") String name,
                                            @RequestParam(value = "editImage", required = false) MultipartFile image,
                                            @RequestParam("editPrice") Integer price,
                                            @RequestParam("editContent") String content){

        System.out.println("Image : " + image);
        System.out.println("수정할 메뉴의 원래 이름 : "+menuName);
        System.out.println("수정 될 메뉴 가격 : " + price);
        System.out.println("수정 될 메뉴 설명 : " + content);

        try{
            System.out.println("Try로 넘어감");
            String imagePath = null;
            String fileName = "";
            String saveDir = "";

            if (image != null){
                //파일 이름 생성
                fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(image.getOriginalFilename());
                saveDir = "/Users/jujaeyoung/desktop/images/";
                Path path = Paths.get(saveDir,fileName);

                // 이미지 파일 저장
                File saveFile = path.toFile();
                image.transferTo(saveFile);

                imagePath = saveDir + fileName;
            }

            System.out.println("adminService로 넘어감");
            ArrayList<Object> isUpdateSuccess = adminService.updateMenu(menuName, name, price, content, imagePath);
            System.out.println("adminService로직 끝");

            if (isUpdateSuccess.get(0).equals(true)){
                System.out.println("이미지 업데이트 성공!");
                System.out.println(isUpdateSuccess.get(0));
                System.out.println(isUpdateSuccess.get(1));

                //기존에 존재하던 이미지 삭제
                if ( image != null ){ //이미지 변수가 있을때만 기존 이미지 파일 삭제
                    adminService.deleteImageFile((String) isUpdateSuccess.get(1));
                }


                return ResponseEntity.ok().body("이미지 업데이트 완료!");
            }else {
                return ResponseEntity.notFound().build();
            }


        }catch(Exception error){
            return ResponseEntity.notFound().build();
        }
    }
}
