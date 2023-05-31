package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.entity.tables;
import LD_Caffe.ld_caffe.service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TtablesController {

    @Autowired
    private TablesService tablesService;

    @GetMapping("/tables")
    public String tablesform(){

        return "tables";
    }

//    @PostMapping("/table/tablespro")
//    public String tablespro(tables tables) {
//
//        tablesService.show(tables);
//
//        return "";
//    }

    @GetMapping("/table/list")
    public String tablesList(Model model) {

        List<tables> result = tablesService.tablesList();
        List<String> colors = new ArrayList<>();


        for(tables i : result){
            Integer useNum = i.getT_use();
            if (useNum == 1){
                colors.add("#FF0000");

            }else {
                colors.add("#04B404");

            }
        }
        // 모델에 값 넣어주기
        model.addAttribute("list", result);
        model.addAttribute("colors", colors);

        System.out.println(colors);

        return "tableslist";
    }

}
