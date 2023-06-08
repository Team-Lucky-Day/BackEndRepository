package LD_Caffe.ld_caffe.controller;


import LD_Caffe.ld_caffe.domain.CardEntity;
import LD_Caffe.ld_caffe.domain.UserEntity;
import LD_Caffe.ld_caffe.dto.DeleteDto;
import LD_Caffe.ld_caffe.dto.LoginDto;
import LD_Caffe.ld_caffe.dto.UserDto;
import LD_Caffe.ld_caffe.repository.CardRepository;
import LD_Caffe.ld_caffe.repository.UserRepository;
import LD_Caffe.ld_caffe.service.UserInfoService;
import LD_Caffe.ld_caffe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

    private final UserService userService;
    public final UserInfoService userInfoService;
    public final CardRepository cardRepository;
    public final UserRepository userRepository;

//    @GetMapping("/list")  // 모든 유저 조회 메서드
//    public String findAllUser(Model model){
//        List<UserEntity> userList = userService.findAllUser();
//        model.addAttribute("userList",userList);
//        return "userList";
//    }

//    @GetMapping("/{u_id}")  // u_id 값으로 특정 유저 조회 메서드
//    public String userInfo(@PathVariable("u_id")String u_id,Model model){
//        Optional<UserEntity> user = userService.findUserById(u_id);
//        model.addAttribute("user",user.get());
//        return "userInfo";
//    }

    @PostMapping("/signup")   // 회원가입 메서드
    public ResponseEntity<String> signUpUser(@RequestBody UserDto userDTO){
        return userService.saveUser(userDTO);  //ResponseEntity 로 반환해준다
    }

    @PostMapping("/IDcheck")  // ID 중복확인 메서드
    public ResponseEntity<LoginDto> isIDPresent(@RequestBody LoginDto loginDto){
        if (userService.isIdExsits(loginDto)){ // 존재한다 400
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok().build();  //존재하지 않는다 OK
        }

    }

    @PostMapping("/login")  // 로그인 메서드
    public ResponseEntity<LoginDto> userLogin(@RequestBody LoginDto loginDto){
        if(userService.userLogin(loginDto)){  // 로그인 성공
            return ResponseEntity.ok().build();
        } else {  // 로그인 실패
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<LoginDto> userLogOut(HttpSession session , LoginDto loginDto){
        session.removeAttribute("");
        return ResponseEntity.ok().build();
    }




//   public final TestService testService;

//    @GetMapping("/error")
//    public String error() {
//
//        return "error";
//    }

//    @GetMapping("/userInfo")
//    public String toUserInfo(Model model){
//
//        List<UserEntity> userData = userRepository.findAll();
//        List<CardEntity> cardData = new ArrayList<>();
////      List<UserEntity> userData = userInfoService.getAllUser();
////      System.out.println(userData.get(0).getUserName());
//
//       for (UserEntity u : userData ){
//           String cardNum = u.getUserCardNum();
////           System.out.println(cardNum);
//           cardData.add(cardRepository.findByCardNumber(cardNum).get(0));
//
//       }
//
//       System.out.println((cardData));
//
//       model.addAttribute("userData",userData);
//       model.addAttribute("cardData",cardData);
//
//        return "getUser";
//    }

//    @PostMapping("/userInfo")
//    public RedirectView getUserInfo(@ModelAttribute UserDto userDto){
//
//        System.out.println(userDto);
//        userInfoService.save(userDto);
//
//
//        return new RedirectView("/userInfo");
//    }


//    @PostMapping("/deleteUser")
//    public RedirectView deleteUserInfo(@ModelAttribute DeleteDto deleteDto, RedirectAttributes redirectAttributes){
//        String userId = deleteDto.getUserId();
//        Optional<UserEntity> result = userRepository.findById(userId);
//        if (result.isPresent()){
//
//            //Optional에서 카드 값만 가져오기
//            String cardNum = result.get().getUserCardNum();
//
//            //User테이블과 Card테이블에서 값 지우기
//            userInfoService.DeleteUser(userId);
//            userInfoService.deleteCard(cardNum);
//
//            System.out.println("UserId => " + userId + " (삭제완료!)");
//
//            return new RedirectView("/userInfo");
//
//        }else{
//            return new RedirectView("/error");
//        }
//
//    }


    // <<<TEST>>>
//    @GetMapping("/test")
//    public String testGet(){
//
//        return "test";
//    }
//
//    @PostMapping("/test")
//    public String testPost(@ModelAttribute TestDto testDto){
//        System.out.println(testDto);
//        testService.save(testDto);
//        return "test";
//    }

}
