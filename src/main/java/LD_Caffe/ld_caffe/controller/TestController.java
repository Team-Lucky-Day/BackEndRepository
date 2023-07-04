package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/color")
    public String color(Model model) {

        List<Integer> using = new ArrayList<>();
        List<String> colors = new ArrayList<>();
        using.add(0);
        using.add(1);
        using.add(1);
        using.add(0);
        using.add(1);

        for (Integer i : using) {
            if (i == 1) {
                colors.add("#04B404");
            } else {
                colors.add("#FF0000");
            }
        }

        model.addAttribute("colors", colors);

        return "test";
    }

    @PostMapping("/finduser")
    public String findUser(Authentication authentication) {
        System.out.println(authentication.getName());
        System.out.println("authentication.getDetails() = " + authentication.getDetails());
        System.out.println("authentication.getAuthorities() = " + authentication.getAuthorities());
        return "OK";
    }
}
