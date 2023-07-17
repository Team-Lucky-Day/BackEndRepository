package LD_Caffe.ld_caffe.repository;

import LD_Caffe.ld_caffe.domain.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity,Integer> {
    List<OrdersEntity> findAllByUserId(String userId);
}
