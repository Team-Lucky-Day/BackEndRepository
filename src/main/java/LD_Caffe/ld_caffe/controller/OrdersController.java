package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.domain.OrdersEntity;
import LD_Caffe.ld_caffe.dto.MenuDto;
import LD_Caffe.ld_caffe.repository.UserRepository;
import LD_Caffe.ld_caffe.service.OrdersService;
import LD_Caffe.ld_caffe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrdersController {

    private final OrdersService ordersService;
    private final UserService userService;



}
