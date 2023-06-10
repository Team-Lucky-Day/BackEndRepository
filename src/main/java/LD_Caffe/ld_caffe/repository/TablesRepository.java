package LD_Caffe.ld_caffe.repository;

import LD_Caffe.ld_caffe.domain.TablesEntity;
import LD_Caffe.ld_caffe.domain.TablesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TablesRepository extends JpaRepository<TablesEntity,Integer >{
}
