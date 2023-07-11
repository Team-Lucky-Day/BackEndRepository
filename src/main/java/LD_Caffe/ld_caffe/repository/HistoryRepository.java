package LD_Caffe.ld_caffe.repository;

import LD_Caffe.ld_caffe.domain.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity,Integer> {
    List<HistoryEntity> findAllByU_id(String userId);
}
