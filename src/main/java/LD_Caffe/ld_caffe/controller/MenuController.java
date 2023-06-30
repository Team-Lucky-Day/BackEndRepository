package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.domain.MenuEntity;
import LD_Caffe.ld_caffe.dto.MenuDto;
import LD_Caffe.ld_caffe.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    public final MenuService menuService;

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

    @PostMapping("/payment")
    public void payment(@RequestBody MenuDto paymentList){

    }
}
