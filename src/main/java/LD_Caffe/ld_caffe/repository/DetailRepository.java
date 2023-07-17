package LD_Caffe.ld_caffe.repository;

import LD_Caffe.ld_caffe.domain.DetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<DetailEntity,Integer> {

    List<DetailEntity> findAllByOrdersCode(int orderCode);


}
