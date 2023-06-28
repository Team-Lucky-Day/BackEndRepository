package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.dto.FavoriteDto;
import LD_Caffe.ld_caffe.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/fav")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;



}
