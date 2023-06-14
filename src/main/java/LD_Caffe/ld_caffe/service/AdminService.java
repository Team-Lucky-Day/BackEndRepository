package LD_Caffe.ld_caffe.service;

import LD_Caffe.ld_caffe.domain.MenuEntity;
import LD_Caffe.ld_caffe.domain.UserEntity;
import LD_Caffe.ld_caffe.dto.MenuDto;
import LD_Caffe.ld_caffe.repository.MenuRepository;
import LD_Caffe.ld_caffe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.event.MenuEvent;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final MenuRepository menuRepository;

    //유저 이름 가져오기
    public ArrayList<String> getUserNames(){

        List<UserEntity> userInfo = userRepository.findAll();
        ArrayList<String> userNames = new ArrayList<>();

        for (UserEntity i : userInfo){
            userNames.add(i.getUserName());
        }

        return userNames;
    }


    public Integer deleteUser(String userName){
        System.out.println(userName);
        // userName로 userId찾아서 그값으로 데이터 삭제
        Optional<UserEntity> userInfo = userRepository.findByuserName(userName);
//        List<UserEntity> userInfo = userRepository.findByuserName(userName);
        if (userInfo.isPresent()){
            System.out.println("데이터베이스에서 유저명을 찾았습니다.");
            String userId = userInfo.get().getUserId();
            userRepository.deleteById(userId);
            return 1;
        }else {
            System.out.println("데이터베이스에서 해당 유저명이 없습니다.");
            return 0;
        }
    }

    public void addMenu(MenuDto menuDto){

        MenuEntity menu = MenuEntity.toMenuEntity(menuDto);
        menuRepository.save(menu);
    }

    public List<MenuDto> getAllMenu(){
        List<MenuEntity> menuInfoList = menuRepository.findAll();
        List<MenuDto> menuInfo = new ArrayList<>();

        for (MenuEntity menu : menuInfoList){
            MenuDto menuDto = new MenuDto();
            menuDto.setCategory(menu.getMenuCategory());
            menuDto.setName(menu.getMenuName());
            menuDto.setContent(menu.getMenuContents());
            menuDto.setPrice(menu.getMenuPrice());
            menuInfo.add(menuDto);
        }

            return menuInfo;
        }


}