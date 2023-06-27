package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.domain.TablesEntity;
import LD_Caffe.ld_caffe.service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TtablesController {

    @Autowired
    private TablesService tablesService;


    @CrossOrigin("http://localhost:3000")
    @RestController
    @RequestMapping("/table")
    public class TableController {

        @Autowired
        private TablesService tablesService;

        @GetMapping("/list")
        public List<Map<String, Object>> tablesList() {
            List<TablesEntity> result = tablesService.tablesList();
            List<Map<String, Object>> tables = new ArrayList<>();

            for (TablesEntity table : result) {
                Map<String, Object> tableMap = new HashMap<>();
                tableMap.put("t_code", table.getT_code());
                tableMap.put("t_use", table.getT_use());
                tableMap.put("t_headcount", table.getT_headcount());
                tableMap.put("color", table.getT_use() == 1 ? "#FF0000" : "#ccc");
                tables.add(tableMap);
            }

            return tables;
        }
    }




    @CrossOrigin("http://localhost:3000")
    @PostMapping("/tables/change")
    public ResponseEntity<String> tablesChange(@RequestBody TablesEntity tablesEntity){
        tablesService.changeSeat(tablesEntity);
        return ResponseEntity.ok("1");
    }

}
