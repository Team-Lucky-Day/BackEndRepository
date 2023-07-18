package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.domain.MenuEntity;
import LD_Caffe.ld_caffe.dto.MenuDto;
import LD_Caffe.ld_caffe.service.AdminService;
import LD_Caffe.ld_caffe.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;
    private final AdminService adminService;

    @GetMapping("/coffee")  // 커피 전체 메뉴 반환
    public ResponseEntity<List<MenuEntity>> getCoffeeList(){
        if(menuService.getList("coffee") != null){
            List<MenuEntity> coffeeList = menuService.getList("coffee");
            return ResponseEntity.ok(coffeeList);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/beverage")  // 음료 전체 메뉴 반환
    public ResponseEntity<List<MenuEntity>> getBeverageList(){
        if(menuService.getList("beverage") != null){
            List<MenuEntity> coffeeList = menuService.getList("beverage");
            return ResponseEntity.ok(coffeeList);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dessert")  // 디저트 전체 메뉴 반환
    public ResponseEntity<List<MenuEntity>> getDessertList(){
        if(menuService.getList("dessert") != null){
            List<MenuEntity> coffeeList = menuService.getList("dessert");
            return ResponseEntity.ok(coffeeList);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/menuList")  // 메뉴 리스트 메서드
    public ResponseEntity<List<MenuDto>> MenuList(Authentication authentication) throws IOException {

        List<MenuDto> menuInfo = adminService.getAllMenuInfo();

        for (MenuDto menu : menuInfo){
            System.out.println(menu.getImageBytes());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(menuInfo, headers, HttpStatus.OK);
    }

}
