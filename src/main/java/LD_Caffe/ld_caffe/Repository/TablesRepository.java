package LD_Caffe.ld_caffe.Repository;

import LD_Caffe.ld_caffe.Entity.tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TablesRepository extends JpaRepository<tables,Integer >{
}
