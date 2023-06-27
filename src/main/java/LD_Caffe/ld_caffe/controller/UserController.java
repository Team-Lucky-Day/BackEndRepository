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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

    private final UserService userService;
    public final UserInfoService userInfoService;
    public final CardRepository cardRepository;
    public final UserRepository userRepository;

    @GetMapping("/list")  // 모든 유저 조회 메서드
    public String findAllUser(Model model){
        List<UserEntity> userList = userService.findAllUser();
        model.addAttribute("userList",userList);
        return "userList";
    }

    @GetMapping("/{u_id}")  // u_id 값으로 특정 유저 조회 메서드
    public String userInfo(@PathVariable("u_id")String u_id,Model model){
        Optional<UserEntity> user = userService.findUserById(u_id);
        model.addAttribute("user",user.get());
        return "userInfo";
    }
    @PostMapping("/login")  // 로그인 메서드
    public ResponseEntity<LoginDto> userLogin(@RequestBody LoginDto loginDto, HttpServletRequest request){
        System.out.println("loginDto.getU_id() = " + loginDto.getU_id());
        System.out.println("loginDto.getU_pw() = " + loginDto.getU_pw());
        if(userService.userLogin(loginDto)){  // 로그인 성공
            HttpSession session = request.getSession();
            session.setAttribute("loginId",loginDto.getU_id());  // 로그인 성공하면 세션 생성
            System.out.println(session.getAttribute("loginId"));
            return ResponseEntity.ok().build();
        } else {  // 로그인 실패
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/signup")   // 회원가입 메서드
    public ResponseEntity<String> signUpUser(@RequestBody UserDto userDTO){
        System.out.println("userDTO = " + userDTO.getU_id());
        userService.saveUser(userDTO);
        return ResponseEntity.ok().build();  //ResponseEntity 로 반환해준다

    }

    @PostMapping("/IDcheck")  // ID 중복확인 메서드
    public ResponseEntity<String> isIDPresent(@RequestBody LoginDto loginDto){
        if (userService.isIdExsits(loginDto)){ // 존재한다 400
            return ResponseEntity.badRequest().body("이미 존재하는 ID 입니다.");
        }else{
            return ResponseEntity.ok("생성가능한 ID 입니다.");  //존재하지 않는다 OK
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<LoginDto> userLogOut(HttpServletRequest request){  // 로그아웃 메서드
        HttpSession session = request.getSession(false);
        if (session != null) {  // 세션이 존재한다면
            session.invalidate(); // 세션 정보를 삭제한다
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }

    @GetMapping("/userInfo")
    public String toUserInfo(Model model){

        List<UserEntity> userData = userRepository.findAll();
        List<CardEntity> cardData = new ArrayList<>();
//      List<UserEntity> userData = userInfoService.getAllUser();
//      System.out.println(userData.get(0).getUserName());

       for (UserEntity u : userData ){
           String cardNum = u.getUserCardNum();
//           System.out.println(cardNum);
           cardData.add(cardRepository.findByCardNumber(cardNum).get(0));

       }

       System.out.println((cardData));

       model.addAttribute("userData",userData);
       model.addAttribute("cardData",cardData);
        return "getUser";
    }

    @PostMapping("/userInfo")
    public RedirectView getUserInfo(@ModelAttribute UserDto userDto){

        System.out.println(userDto);
        userInfoService.save(userDto);


        return new RedirectView("/userInfo");
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/deleteUser")
    public RedirectView deleteUserInfo(@ModelAttribute DeleteDto deleteDto, RedirectAttributes redirectAttributes){
        String userId = deleteDto.getUserId();
        Optional<UserEntity> result = userRepository.findById(userId);
        if (result.isPresent()){

            //Optional에서 카드 값만 가져오기
            String cardNum = result.get().getUserCardNum();

            //User테이블과 Card테이블에서 값 지우기
            userInfoService.DeleteUser(userId);
            userInfoService.deleteCard(cardNum);

            System.out.println("UserId => " + userId + " (삭제완료!)");

            return new RedirectView("/userInfo");

        }else{
            return new RedirectView("/error");
        }

    }


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
