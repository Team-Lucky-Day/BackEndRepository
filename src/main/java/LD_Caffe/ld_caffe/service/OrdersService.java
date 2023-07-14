package LD_Caffe.ld_caffe.service;

import LD_Caffe.ld_caffe.domain.DetailEntity;
import LD_Caffe.ld_caffe.domain.OrdersEntity;
import LD_Caffe.ld_caffe.dto.OrderDto;
import LD_Caffe.ld_caffe.repository.DetailRepository;
import LD_Caffe.ld_caffe.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final DetailRepository detailRepository;

    public List<OrdersEntity> allOrders(){
        return ordersRepository.findAll();
    }

    public Optional<OrdersEntity> findOrderById(int i){
        return ordersRepository.findById(i);
    }

    public void saveOrderHistory(OrderDto orderDto,String userId){
        OrdersEntity ordersEntity = OrdersEntity.toOrdersEntity(userId); // Order 엔티티 생성하고 DB에 저장
        ordersRepository.save(ordersEntity);

        for (int menuCode : orderDto.getOrderList().keySet()){  // 메뉴 코드와 메뉴 수량을 전부 가져오는 코드
            int menuCount = orderDto.getOrderList().get(menuCode);
            // 주문으로 들어온 모든 주문들을 DB 로 저장한다.
            DetailEntity detailEntity = DetailEntity.toDetailEntity(menuCode,menuCount, ordersEntity.getO_code());
            detailRepository.save(detailEntity);
        }
        System.out.println(ordersEntity.getO_code()+"번 주문 처리완료.");
    }
}
