package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.repository.UserRepository;
import LD_Caffe.ld_caffe.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/users/delete/{id}")
    public Integer deleteUserInfo(@PathVariable String userId){

        Integer result = adminService.deleteUser(userId);

        if (result == 1){
            return result;
        }else {
            return 0;
        }

//        삭제처리 완료 => 1
//        삭체처리 실패 => 0
    }

}
