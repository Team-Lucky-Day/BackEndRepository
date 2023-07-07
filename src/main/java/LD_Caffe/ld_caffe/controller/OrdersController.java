package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.service.OrdersService;
import LD_Caffe.ld_caffe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrdersController {

    private final OrdersService ordersService;
    private final UserService userService;



}
