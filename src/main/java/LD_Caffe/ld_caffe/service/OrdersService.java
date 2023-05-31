package LD_Caffe.ld_caffe.service;

import LD_Caffe.ld_caffe.domain.OrdersEntity;
import LD_Caffe.ld_caffe.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public List<OrdersEntity> allOrders(){
        return ordersRepository.findAll();
    }

    public Optional<OrdersEntity> findOrderById(int i){
        return ordersRepository.findById(i);
    }



}
