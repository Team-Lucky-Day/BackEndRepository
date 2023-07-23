package LD_Caffe.ld_caffe.service;

import LD_Caffe.ld_caffe.domain.DetailEntity;
import LD_Caffe.ld_caffe.domain.OrdersEntity;
import LD_Caffe.ld_caffe.dto.ChartDto;
import LD_Caffe.ld_caffe.dto.DetailResponseDto;
import LD_Caffe.ld_caffe.dto.OrderDto;
import LD_Caffe.ld_caffe.dto.OrderResponseDto;
import LD_Caffe.ld_caffe.repository.DetailRepository;
import LD_Caffe.ld_caffe.repository.MenuRepository;
import LD_Caffe.ld_caffe.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final DetailRepository detailRepository;
    private final MenuRepository menuRepository;

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

    public List<OrderResponseDto> getOrderHistories(String userId){  // 주문목록 조회하는 메서드
        List<OrdersEntity> ordersEntityList = ordersRepository.findAllByUserId(userId); // 토큰에 있는 Id값으로 주문목록 조회
        if (ordersEntityList==null){  // 주문목록이 비어있다면 404 에러 발생
            return new ArrayList<OrderResponseDto>();
        }
        List<OrderResponseDto> finalList = new ArrayList<>(); // 마지막에 HTTP body 에 담길 리스트
        for (OrdersEntity i : ordersEntityList) {
            List<DetailEntity> detailEntities = detailRepository.findAllByOrdersCode(i.getO_code()); // entity -> dto
            List<DetailResponseDto> detailResponseDtoList = new ArrayList<>();
            for (DetailEntity j : detailEntities) {  // DTO 만들기
                DetailResponseDto dto = DetailResponseDto.builder()
                        .ordersCode(j.getOrdersCode())
                        .detailCount(j.getDetailCount())
                        .detailPrice((menuRepository.findById(j.getMenuCode()).get().getMenuPrice()*(j.getDetailCount())))
                        .userName(userId)
                        .build();
                dto.setMenuName(menuRepository.findById(j.getMenuCode()).get().getMenuName());
                detailResponseDtoList.add(dto);
            }
            OrderResponseDto dto = OrderResponseDto.builder()
                    .orderCode(i.getO_code())
                    .orderDate(i.getO_date())
                    .details(detailResponseDtoList)
                    .build();
            finalList.add(dto);
        }
        return finalList;
    }

    public Map<String,Integer> getCategoryAndCount(int orderCode){ // 카테고리와 수량을 리턴하는 메서드
        List<DetailEntity> detailList = detailRepository.findAllByOrdersCode(orderCode); // detail 리스트
        Map<String,Integer> categoryAndCount = new HashMap<>(); // 새로운 Map 생성
        for (DetailEntity i : detailList) {
            String category = menuRepository.findById(i.getMenuCode()).get().getMenuCategory();
            int count = i.getDetailCount();
            if (categoryAndCount.get(category) == null){ // category 를 Key 로 하는 Value 가 null 이면
                categoryAndCount.put(category,count);  // 새로 삽입
            }else{
                int tmp = categoryAndCount.get(category); // 아니면 원래 있던 value 에 count 를 더해서 삽입
                categoryAndCount.put(category,tmp+count);
            }
        }
        return categoryAndCount;
    }


}
