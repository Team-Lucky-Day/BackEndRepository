package LD_Caffe.ld_caffe.service;

import LD_Caffe.ld_caffe.domain.DetailEntity;
import LD_Caffe.ld_caffe.domain.MenuEntity;
import LD_Caffe.ld_caffe.domain.OrdersEntity;
import LD_Caffe.ld_caffe.dto.ChartDto;
import LD_Caffe.ld_caffe.dto.DetailResponseDto;
import LD_Caffe.ld_caffe.dto.OrderDto;
import LD_Caffe.ld_caffe.dto.OrderResponseDto;
import LD_Caffe.ld_caffe.repository.DetailRepository;
import LD_Caffe.ld_caffe.repository.MenuRepository;
import LD_Caffe.ld_caffe.repository.OrdersRepository;
import LD_Caffe.ld_caffe.repository.UserRepository;
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
    private final UserRepository userRepository;

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

        String userName = userRepository.findById(userId).get().getUserName(); // 유저명이름 찾기

        List<OrderResponseDto> finalList = new ArrayList<>(); // 마지막에 HTTP body 에 담길 리스트

        for (OrdersEntity i : ordersEntityList) {
            List<DetailEntity> detailEntities = detailRepository.findAllByOrdersCode(i.getO_code()); // entity -> dto
            List<DetailResponseDto> detailResponseDtoList = new ArrayList<>();

            for (DetailEntity j : detailEntities) {  // DTO 만들기
                DetailResponseDto dto = DetailResponseDto.builder()
                        .ordersCode(j.getOrdersCode())
                        .detailCount(j.getDetailCount())
                        .detailPrice((menuRepository.findById(j.getMenuCode()).get().getMenuPrice()*(j.getDetailCount())))
                        .userName(userName)
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


    public Map<String, Map<String, Integer>> getMonthlyOrder(List<Integer> orderCodes){
        System.out.println("서비스 메서드 진입완료");

        Map<String, Map<String, Integer>> monthlyOrder = new HashMap<>(); // 모든 값을 담을 HashMap 생성


        try {



            for (Integer code : orderCodes){ //주문코드 별 카테고리들의 개수를 카툰트해서 HashMap에 저장하기
                // 주문코드에 해당하는 디테일 정보 가져오기
                List<DetailEntity> orderInfo = detailRepository.findAllByOrdersCode(code);
                System.out.println("디테일레포지토리 주문 정보들 가지고오기 성공!" + orderInfo);
                Integer coffeeCount = 0;
                Integer beverageCount = 0;
                Integer dessertCount = 0;
                Map<String, Integer> categoryOrder = new HashMap<>();


                // 주문 날짜 가져와서 그중 월만 뽑아서 사용하기 => 달에 따른 카테고리별 수량을 체크하기 위함
                Optional<OrdersEntity> ordersEntity = ordersRepository.findById(code);
                Date orderDate = ordersEntity.get().getO_date();
                Calendar date = Calendar.getInstance();
                date.setTime(orderDate);
                String month = String.valueOf(date.get(Calendar.MONTH) + 1); // 월만 따로 저장


                System.out.println("날짜 구하기 완료 & 반복문 진입 직전");
                // 디테일 정보
                for (DetailEntity info : orderInfo){
                    System.out.println("반복문 진입");
                    Integer menuCode = info.getMenuCode();
                    Optional<MenuEntity> menuEntity = menuRepository.findById(menuCode);
                    String menuCategory = menuEntity.get().getMenuCategory();
                    System.out.println("해당 메뉴의 카테고리 >>> "+ menuCategory);

                    if (menuCategory.equals("coffee")) {
                        coffeeCount += info.getDetailCount();
                    } else if (menuCategory.equals("beverage")) {
                        beverageCount += info.getDetailCount();
                    }else {
                        dessertCount += info.getDetailCount();
                    }

                    System.out.println("coffee count : " + coffeeCount);
                    System.out.println("beverage count : " + beverageCount);
                    System.out.println("dessert count : " + dessertCount);

                }
                if (monthlyOrder.containsKey(month)){ // 해당 달이 이미 존재 할 경우
                    Map<String, Integer> items =  monthlyOrder.get(month);

                    Integer finalCoffeeCount = coffeeCount;
                    Integer finalBeverageCount = beverageCount;
                    Integer finalDessertCount = dessertCount;

                    items.forEach( (category, count) -> {
                        if (category.equals("coffee")){
                            items.put(category, count + finalCoffeeCount);
                        } else if (category.equals("beverage")) {
                            items.put(category, count + finalBeverageCount);
                        }else {
                            items.put(category, count + finalDessertCount);
                        }

                    });



                }else { // 해당 달이 존재하지 않을 경우
                    categoryOrder.put("coffee", coffeeCount);
                    categoryOrder.put("beverage", beverageCount);
                    categoryOrder.put("dessert", dessertCount);
                    monthlyOrder.put(month, categoryOrder);
                }

            }


            System.out.println("작업 완료!!");
            return monthlyOrder;
        }catch (Exception error)
        {
            System.out.println(error.getMessage());
            return monthlyOrder;
        }

    }

    public List<Integer> getAllOrderCode(String userCode) {
        List<Integer> orderCodes = new ArrayList<>();

        List<OrdersEntity> result = ordersRepository.findAllByUserId(userCode);
        for (OrdersEntity orderInfo : result) {
            orderCodes.add(orderInfo.getO_code());
        }

        return orderCodes;
    }
}
