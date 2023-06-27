package LD_Caffe.ld_caffe.service;


import LD_Caffe.ld_caffe.domain.TablesEntity;
import LD_Caffe.ld_caffe.repository.TablesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TablesService {


    private final TablesRepository tablesRepository;

    public void show(TablesEntity tablesEntity) {

        tablesRepository.save(tablesEntity);
    }

    public List<TablesEntity> tablesList() {

        return tablesRepository.findAll();



    }

    public void changeSeat(TablesEntity tablesEntity) {
        TablesEntity entity = tablesRepository.findById(tablesEntity.getT_code()).get();
        if (entity.getT_use()==0){
            entity.setT_use(1);
            tablesRepository.save(entity);
            System.out.println(entity);

        }else if(entity.getT_use()==1){
            entity.setT_use(0);
            tablesRepository.save(entity);
            System.out.println(entity);
        }

    }
}