package LD_Caffe.ld_caffe.Service;


import LD_Caffe.ld_caffe.Entity.tables;
import LD_Caffe.ld_caffe.Repository.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TablesService {

    @Autowired
    private TablesRepository tablesRepository;

    public void show(tables tables) {

        tablesRepository.save(tables);

    }

    public List<tables> tablesList() {

        return tablesRepository.findAll();




    }
}
