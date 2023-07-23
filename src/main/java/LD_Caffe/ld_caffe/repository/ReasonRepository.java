package LD_Caffe.ld_caffe.repository;

import LD_Caffe.ld_caffe.domain.ReasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReasonRepository extends JpaRepository<ReasonEntity, Integer> {

}
