package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.dto.FavoriteDto;
import LD_Caffe.ld_caffe.dto.FavoriteEnrollDto;
import LD_Caffe.ld_caffe.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/fav")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // 서버에서 CORS 관리할때 사용
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/enrollment")
    //즐겨찾기 등록
    public ResponseEntity<String> updateFavoriteMenu(@RequestBody FavoriteEnrollDto favEnrollDto,
                                                     Authentication authentication){

        System.out.println("menuCode : "+favEnrollDto.getMenuCode());
        String userCode = authentication.getName();
        int menuCode = favEnrollDto.getMenuCode();
        boolean isUpdateOk = favoriteService.enrollFavorite(menuCode, userCode);

        if (isUpdateOk){
            return ResponseEntity.ok("즐겨찾기 등록 완료");
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
