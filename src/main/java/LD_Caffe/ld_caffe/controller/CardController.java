package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.dto.OrderDto;
import LD_Caffe.ld_caffe.service.CardService;
import LD_Caffe.ld_caffe.service.OrdersService;
import LD_Caffe.ld_caffe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;
    private final UserService userService;
    private final OrdersService ordersService;

    @PostMapping("/payment")  // 결제 메서드?
    public ResponseEntity<String> payment(@RequestBody OrderDto orderDto, Authentication authentication){
        String userCardNum = "";
        userCardNum = userService.findUserById(authentication.getName()).get().getUserCardNum();
        System.out.println(userCardNum); // 카드번호 가져오기
        if (cardService.isCardVaild(userCardNum)) {  // 카드번호가 유효한지 검증
            ordersService.saveOrderHistory(orderDto,authentication.getName()); // OrderService의 주문내역 저장 메서드 호출


            return ResponseEntity.ok("결제 완료");
        } else {
            return ResponseEntity.status(403).body("결제에 실패하였습니다");
        }

    }
}