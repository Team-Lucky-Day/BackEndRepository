package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.domain.MenuEntity;
import LD_Caffe.ld_caffe.dto.MenuDto;
import LD_Caffe.ld_caffe.repository.MenuRepository;
import LD_Caffe.ld_caffe.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Controller
@CrossOrigin(origins = "http://localhost:3000") // 서버에서 CORS 관리할때 사용
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    public final AdminService adminService;
    private final MenuRepository menuRepository;

    @ResponseBody
    @GetMapping("/users")
    public ArrayList<String> AlluserInfo(){

//        로그인한 사람이 관리자 계정인지 확인하기

//        ArrayList<String> usersInfo = new ArrayList<>();
//        usersInfo.add("TestData1");
//        usersInfo.add("TestData2");
//        usersInfo.add("TestData3");

        return adminService.getUserNames();
    }

    @DeleteMapping("/users/delete/{name}")
    public ResponseEntity<String> deleteUserInfo(@PathVariable String name){
        System.out.println(name);

        Integer result = adminService.deleteUser(name);
        System.out.println("삭제결과" + result);
        if (result == 1){
            System.out.println("데이터 찾음 // 데이터 삭제 완료");
            return ResponseEntity.ok("데이터 삭제 완료");
        }else {
            System.out.println("데이터 없음 // 데이터 삭제 실패");
            return null;
        }

    }

    // 메뉴 데이터베이스 저장
    @PostMapping("/menu")
    public ResponseEntity<String> addMenu(@RequestParam("image") MultipartFile file,
                                          @RequestParam("category") String category,
                                          @RequestParam("name") String name,
                                          @RequestParam("content") String content,
                                          @RequestParam("price") Integer price){

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

    @ResponseBody
    @GetMapping("/menuList")
    public ResponseEntity<List<MenuDto>> MenuList() throws IOException {

        List<MenuDto> menuInfo = adminService.getAllMenuInfo();

        for (MenuDto menu : menuInfo){
            System.out.println(menu.getImageBytes());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(menuInfo, headers, HttpStatus.OK);
    }

    @DeleteMapping("/menu/delete/{menuName}")
    public ResponseEntity<String> deleteMenuInfo(@PathVariable String menuName){
        System.out.println(menuName);

        try{

            Integer result = adminService.deleteMenu(menuName);

            if (result == 1){
                System.out.println(" 메뉴 삭제 완료");
                return ResponseEntity.ok("데이터 삭제 완료");
            }else {
                System.out.println("데이터 없음 // 데이터 삭제 실패");
                return null;
            }

        }catch(Exception error){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("메뉴 삭제 실패");
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
