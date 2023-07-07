package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.dto.TestDto;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController // @RestController => @Controller + @ResponseBody
//@RequestMapping("/test")
@CrossOrigin(origins = "http://localhost:3000") // 서버에서 CORS 관리할때 사용
public class ApiController {

    @GetMapping("/board/insert")
    public void boardinsert(@RequestParam Integer id, @RequestParam String title) {

        System.out.println(id);
        System.out.println(title);
    }

    @GetMapping("/test/api")
    public String boardList(){
        return "데이터 전송 성공";
    }
    @PostMapping("/test/post")
    public void postValue(@RequestBody TestDto testDto){
        System.out.println("숫자 : " + testDto.getTestNum());
        System.out.println("String : " + testDto.getTestString());
    }
}

//    static class ExampleData{
//        private int id;
//        private String name;
//
//        public ExampleData(int id, String name){
//            this.id = id;
//            this.name = name;
//        }
//    }

