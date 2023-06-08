package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.domain.TablesEntity;
import LD_Caffe.ld_caffe.service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TtablesController {

    @Autowired
    private TablesService tablesService;


    @CrossOrigin("http://localhost:3000")
    @GetMapping("/table/list")
    public String tablesList(Model model) {

        List<TablesEntity> result = tablesService.tablesList();
        List<String> colors = new ArrayList<>();


        for (TablesEntity i : result) {
            Integer useNum = i.getT_use();
            if (useNum == 1) {
                colors.add("#FF0000");

            } else {
                colors.add("#04B404");

            }
        }
        // 모델에 값 넣어주기
        model.addAttribute("list", result);
        model.addAttribute("colors", colors);

        return "tableslist";
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/tables/change")
    public ResponseEntity<String> tablesChange(@RequestBody TablesEntity tablesEntity){
        tablesService.changeSeat(tablesEntity);
        return ResponseEntity.ok("1");
    }
}