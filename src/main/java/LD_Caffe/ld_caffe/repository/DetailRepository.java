package LD_Caffe.ld_caffe.repository;

import LD_Caffe.ld_caffe.domain.DetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends JpaRepository<DetailEntity,Integer> {


}
