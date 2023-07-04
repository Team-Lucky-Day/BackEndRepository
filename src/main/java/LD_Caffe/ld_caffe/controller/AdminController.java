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
            String saveDir = "/Users/jujaeyoung/desktop/images";
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



    @PostMapping("/test")
    public ResponseEntity<String> test(@RequestParam("image") MultipartFile file){

        System.out.println("File Name : " + file.getOriginalFilename());

        System.out.println("파일 서버저장 시작");
        try{

            // 파일 이름 생성
            String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(file.getOriginalFilename());

            // 이미지 파일 저장 경로
            String saveDir = "/Users/jujaeyoung/desktop/images";
            Path filePath = Paths.get(saveDir, fileName);

            // 이미지 파일 저장
            File saveFile = filePath.toFile();
            file.transferTo(saveFile);

            return ResponseEntity.ok("서버 저장 성공");

        }catch (Exception error){
            System.err.println(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("경로 저장 실패");
        }

    }

    @GetMapping("/getTest")
    public ResponseEntity<byte[]> getImage() throws IOException{

        List<MenuEntity> menuInfoList = menuRepository.findAll();
        String imagePath = menuInfoList.get(10).getMenuImagePath();


//        File file = new File("/Users/jujaeyoung/desktop/images/c1.jpg");
        File file = new File(imagePath);
        byte[] imageBytes = Files.readAllBytes(file.toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imageBytes, headers ,HttpStatus.OK);
    }

}
