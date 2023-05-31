package LD_Caffe.ld_caffe.service;

import LD_Caffe.ld_caffe.entity.Orders;
import LD_Caffe.ld_caffe.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public List<Orders> allOrders(){
        return ordersRepository.findAll();
    }

    public Optional<Orders> findOrderById(int i){
        return ordersRepository.findById(i);
    }



}
