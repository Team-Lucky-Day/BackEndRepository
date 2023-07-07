package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.dto.LoginDto;
import LD_Caffe.ld_caffe.dto.UserDto;
import LD_Caffe.ld_caffe.repository.CardRepository;
import LD_Caffe.ld_caffe.repository.UserRepository;
import LD_Caffe.ld_caffe.service.UserInfoService;
import LD_Caffe.ld_caffe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

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

    @PostMapping("/login")  // 로그인 메서드
    public ResponseEntity<String> userLogin(@RequestBody LoginDto loginDto,HttpServletRequest request){
        System.out.println("loginDto.getU_id() = " + loginDto.getU_id());
        System.out.println("loginDto.getU_pw() = " + loginDto.getU_pw());
        String token = userService.createJwt(loginDto);
        if(token.equals("0")){
            return ResponseEntity.badRequest().build();
        }else{
            System.out.println("token = " + token);
            return ResponseEntity.ok().body(token);
        }
    }

    @PostMapping("/getName")  // user 이름 리턴해주는 메서드
    public ResponseEntity<String> getUserName(Authentication authentication) {
        String userName = userService.findUserById(authentication.getName()).get().getUserName();
        return ResponseEntity.ok().body(userName);
    }


}
