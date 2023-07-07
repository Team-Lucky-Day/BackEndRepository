package LD_Caffe.ld_caffe.controller;

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

        if (adminService.deleteUser(name)){
            System.out.println("데이터 찾음 // 데이터 삭제 완료");
            return ResponseEntity.ok("데이터 삭제 완료");
        }else {
            System.out.println("데이터 없음 // 데이터 삭제 실패");
            return ResponseEntity.notFound().build();
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



}
