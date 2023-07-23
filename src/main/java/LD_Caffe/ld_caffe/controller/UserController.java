package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.domain.ReasonEntity;
import LD_Caffe.ld_caffe.dto.*;
import LD_Caffe.ld_caffe.repository.ReasonRepository;
import LD_Caffe.ld_caffe.service.FavoriteService;
import LD_Caffe.ld_caffe.service.OrdersService;
import LD_Caffe.ld_caffe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.TrueFalseType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

    private final UserService userService;
    private final FavoriteService favoriteService;
    private final OrdersService ordersService;

    @PostMapping("/signup")   // 회원가입 메서드
    public ResponseEntity<String> signUpUser(@RequestBody UserDto userDTO) {
        System.out.println("userDTO = " + userDTO.getU_id());
        userService.saveUser(userDTO);
        return ResponseEntity.ok().build();  //ResponseEntity 로 반환해준다

    }

    @PostMapping("/IDcheck")  // ID 중복확인 메서드
    public ResponseEntity<String> isIDPresent(@RequestBody LoginDto loginDto) {
        if (userService.isIdExsits(loginDto)) { // 존재한다 400
            return ResponseEntity.badRequest().body("이미 존재하는 ID 입니다.");
        } else {
            return ResponseEntity.ok("생성가능한 ID 입니다.");  //존재하지 않는다 OK
        }
    }

    @PostMapping("/login")  // 로그인 메서드
    public ResponseEntity<String> userLogin(@RequestBody LoginDto loginDto) {
        System.out.println("loginDto.getU_id() = " + loginDto.getU_id());
        System.out.println("loginDto.getU_pw() = " + loginDto.getU_pw());
        String token = userService.createJwt(loginDto);
        if (token.equals("0")) {
            return ResponseEntity.badRequest().build();
        } else {
            System.out.println("token = " + token);
            return ResponseEntity.ok().body(token);
        }
    }

    @PostMapping("/getName")  // user 이름 리턴해주는 메서드
    public ResponseEntity<String> getUserName(Authentication authentication) {
        String userName = userService.findUserById(authentication.getName()).get().getUserName();
        return ResponseEntity.ok().body(userName);
    }

    @PostMapping("/favorite") // 즐겨찾기 목록 메서드
    public ResponseEntity<List<FavoriteDto>> getAllFavoriteMenu(Authentication authentication) throws IOException {

        String userCode = authentication.getName();
        System.out.println("유저 코드 : " + userCode);
        String userName = userService.findUserById(userCode).get().getUserName();


        List<FavoriteDto> allFavoriteMenuInfo = favoriteService.getFavoriteMenu(userCode,userName);

        return new ResponseEntity<>(allFavoriteMenuInfo, HttpStatus.OK);
    }

    @PostMapping("/orderHistories") // 주문 목록 조회 메서드
    public ResponseEntity<List<OrderResponseDto>> getAllOrderHistories(Authentication authentication){
        return ordersService.getOrderHistories(authentication.getName()); 
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<Boolean> deleteUser(Authentication authentication,
                                              @RequestBody WithdrawalDto deleteInfo){
        System.out.println("탈퇴 사유 : "+deleteInfo.getReason());
        System.out.println("삭제할 유저의 패스워드 : " + deleteInfo.getPassword());
        Boolean result = userService.withdrawalUser(
                authentication.getName(),
                deleteInfo.getReason(),
                deleteInfo.getPassword()
        );

        return ResponseEntity.ok().body(result);
    }

    private final ReasonRepository reasonRepository;
    @PostMapping("/reason")
    public ResponseEntity<Boolean> saveReason(@RequestBody WithdrawalDto dto,
                                              Authentication authentication){
        try {
            System.out.println("User Name >>> "+authentication.getName());
            ReasonEntity reasonEntity = new ReasonEntity();
            reasonEntity.setReason(dto.getReason());
            reasonRepository.save(reasonEntity);

            return ResponseEntity.ok().body(true);
        } catch(Exception error){
            System.out.println("ERROR >>> "+error);
            return ResponseEntity.status(404).build();
        }

    }

}
