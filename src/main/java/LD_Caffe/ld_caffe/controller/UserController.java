package LD_Caffe.ld_caffe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String userInfo(Model model) {
        return "userTest";
    }

}
