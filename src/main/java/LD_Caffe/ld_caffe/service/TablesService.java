package LD_Caffe.ld_caffe.service;


import LD_Caffe.ld_caffe.domain.TablesEntity;
import LD_Caffe.ld_caffe.repository.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TablesService {

    @Autowired
    private TablesRepository tablesRepository;

    public void show(TablesEntity tablesEntity) {

        tablesRepository.save(tablesEntity);
    }

    public List<TablesEntity> tablesList() {

        return tablesRepository.findAll();
    }
}
