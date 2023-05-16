package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.entity.Orders;
import LD_Caffe.ld_caffe.repository.UserRepository;
import LD_Caffe.ld_caffe.service.OrdersService;
import LD_Caffe.ld_caffe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.OptionalInt;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrdersController {

    private final OrdersService ordersService;
    private final UserService userService;

    @GetMapping("/list")  // 전체 주문 목록
    public String orderList(Model model){
        model.addAttribute("orders",ordersService.allOrders());
        return "orderList";
    }

    @GetMapping("/{o_code}")  // 주문 상세 조회
    public String showOrderDetail(@PathVariable("o_code") int o_code, Model model){
        Orders order = ordersService.findOrderById(o_code).get();
        model.addAttribute("order",order);
        model.addAttribute("user",userService.findUserById(order.getU_id()).get());
        return "orderDetail";
    }

}
