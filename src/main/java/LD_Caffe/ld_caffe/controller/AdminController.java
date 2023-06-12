package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.repository.UserRepository;
import LD_Caffe.ld_caffe.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.Array;
import java.util.ArrayList;


@Controller
@CrossOrigin(origins = "http://localhost:3000") // 서버에서 CORS 관리할때 사용
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    public final AdminService adminService;

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

}
