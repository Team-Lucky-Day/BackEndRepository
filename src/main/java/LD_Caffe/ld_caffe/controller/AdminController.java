package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.domain.MenuEntity;
import LD_Caffe.ld_caffe.dto.MenuDto;
import LD_Caffe.ld_caffe.repository.MenuRepository;
import LD_Caffe.ld_caffe.repository.UserRepository;
import LD_Caffe.ld_caffe.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
    public ResponseEntity<String> addMenu(@RequestBody MenuDto menuDto){

        System.out.println("<< 추가할 메뉴 >>");
        System.out.println("Category : " + menuDto.getCategory());
        System.out.println("Menu Name : " + menuDto.getName());
        System.out.println("Menu Content : " + menuDto.getContent());
        System.out.println("Menu Price : " + menuDto.getPrice());


        try { // 데이터베이스에 잘 저장 되었을 때
            adminService.addMenu(menuDto);
            return ResponseEntity.ok("메뉴 추가 성공");

        }catch (Exception error) { // 데이터베이스에 저장 실패 했을 때
            System.err.println("데이터베이스 저장 실패" + error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("데이터베이스 저장 실패");
        }
    }

    @ResponseBody
    @GetMapping("/menuList")
    public List<MenuDto> MenuList(){

        List<MenuDto> allMenu = adminService.getAllMenu();
//        System.out.println(allMenu.get(0));

        return allMenu;
    }

}
