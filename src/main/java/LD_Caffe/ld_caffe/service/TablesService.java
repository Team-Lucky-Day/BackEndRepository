package LD_Caffe.ld_caffe.service;


import LD_Caffe.ld_caffe.domain.TablesEntity;
import LD_Caffe.ld_caffe.dto.TableDto;
import LD_Caffe.ld_caffe.repository.TablesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<TableDto> getTableState() {

        List<TablesEntity> allTablesInfo = tablesRepository.findAll();
        List<TableDto> result = new ArrayList<>();

        for (TablesEntity tableInfo : allTablesInfo){

            TableDto tableDto = new TableDto();
            tableDto.setTableNumber(tableInfo.getT_code());
            if (tableInfo.getT_use() == 1){
                tableDto.setUseTable(true);
            }else {
                tableDto.setUseTable(false);
            }
            tableDto.setHeadCount(tableInfo.getT_headcount());

            result.add(tableDto);
        }

        return result;
    }

    public Boolean changeState(Integer seatNumber,
                               Boolean seatState) {

        Optional<TablesEntity> tableInfo = tablesRepository.findById(seatNumber);

        if(tableInfo.isPresent()){
            try{
                TablesEntity tableToUpdate = tableInfo.get();

                if (seatState == true){
                    tableToUpdate.setT_use(0);
                }else {
                    tableToUpdate.setT_use(1);
                }

                tablesRepository.save(tableToUpdate);
                return true;
            }catch (Exception error) {
                System.out.println(error.getMessage());
                return false;
            }

        }else {
            return false;
        }
    }
}
