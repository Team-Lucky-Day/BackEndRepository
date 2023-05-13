package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.entity.User;
import LD_Caffe.ld_caffe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")  // 모든 유저 조회 메서드
    public String findAllUser(Model model){
        List<User> userList = userService.findAllUser();
        model.addAttribute("userList",userList);
        return "userList";
    }

    @GetMapping("/{u_id}")  // u_id 값으로 특정 유저 조회 메서드
    public String userInfo(@PathVariable("u_id")String u_id,Model model){
        Optional<User> user = userService.findUserById(u_id);
        model.addAttribute("user",user.get());
        return "userInfo";
    }

}
