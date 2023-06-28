package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.domain.MenuEntity;
import LD_Caffe.ld_caffe.dto.MenuDto;
import LD_Caffe.ld_caffe.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    public final MenuService menuService;

    @GetMapping("/coffee")
    @ResponseBody
    public ResponseEntity<List<MenuEntity>> getCoffeeList(){

        try{
            List<MenuEntity> coffeeList = menuService.getList("coffee");
            return ResponseEntity.ok(coffeeList);
        }catch(Exception error){
            System.err.println("Error Massage : "+error.getMessage());
            return null;
        }

    }

    @GetMapping("/beverage")
    @ResponseBody
    public ResponseEntity<List<MenuEntity>> getBeverageList(){

        try{
            List<MenuEntity> beverageList = menuService.getList("beverage");
            return ResponseEntity.ok(beverageList);
        }catch (Exception error){
            System.err.println("Error Massage : " + error.getMessage());
            return null;
        }
    }

    @GetMapping("/dessert")
    @ResponseBody
    public ResponseEntity<List<MenuEntity>> getDessertList(){

        try{
            List<MenuEntity> dessertList = menuService.getList("dessert");
            return ResponseEntity.ok(dessertList);
        }catch(Exception error){
            System.err.println("Error Massage : " + error.getMessage());
            return null;
        }
    }

    @PostMapping("/payment")
    public void payment(@RequestBody MenuDto paymentList){

    }


}
