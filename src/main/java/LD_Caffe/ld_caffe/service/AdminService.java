package LD_Caffe.ld_caffe.service;

import LD_Caffe.ld_caffe.domain.MenuEntity;
import LD_Caffe.ld_caffe.domain.UserEntity;
import LD_Caffe.ld_caffe.dto.LoginDto;
import LD_Caffe.ld_caffe.dto.MenuDto;
import LD_Caffe.ld_caffe.repository.MenuRepository;
import LD_Caffe.ld_caffe.repository.UserRepository;
import LD_Caffe.ld_caffe.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.swing.event.MenuEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Array;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminService {
    @Value("${jwt.secret}")
    private String secretKey;

    private final long expiredMs = 1000 * 60 * 30L;

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


    public boolean deleteUser(String userName){
        System.out.println(userName);
        // userName로 userId찾아서 그값으로 데이터 삭제
        Optional<UserEntity> userInfo = userRepository.findByuserName(userName);
//        List<UserEntity> userInfo = userRepository.findByuserName(userName);
        if (userInfo.isPresent()){
            System.out.println("데이터베이스에서 유저명을 찾았습니다.");
            String userId = userInfo.get().getUserId();
            userRepository.deleteById(userId);
            return true;
        }else {
            System.out.println("데이터베이스에서 해당 유저명이 없습니다.");
            return false;
        }
    }

    public void addMenu(MenuDto menuDto){

        MenuEntity menu = MenuEntity.toMenuEntity(menuDto);
        menuRepository.save(menu);
    }

    public List<MenuDto> getAllMenuInfo() throws IOException {
        List<MenuEntity> menuInfoList = menuRepository.findAll();
        List<MenuDto> menuInfo = new ArrayList<>();

        for (MenuEntity menu : menuInfoList){

//            System.out.println(menu.getMenuImagePath());

            MenuDto menuDto = new MenuDto();
            menuDto.setCategory(menu.getMenuCategory());
            menuDto.setName(menu.getMenuName());
            menuDto.setContent(menu.getMenuContents());
            menuDto.setPrice(menu.getMenuPrice());

            //경로를 가져와서 이미지를 바이트값으로 바꿔서 DTO에 해당 이미지에 대한 바이트값 넣어주기
            String imagePath = menu.getMenuImagePath();
            File file = new File(imagePath);
            byte[] imageBytes = Files.readAllBytes(file.toPath());


            //이미지 바이트를 Base64로 인코딩하고 Dto에 추가
            // 서버에서 Base64로 인코딩하면 리엑트에서 인코딩할 필요가 없음
//            하지만 지금은 리엑트에서 Base64로 인코딩하기 때문에 서버에서는 인코딩 할 필요가 없다.
//            String encodedImage = Base64.getEncoder().encodeToString(imageBytes);
//            menuDto.setImageBytes(encodedImage.getBytes());
//            menuDto.setImageType("MediaType.IMAGE_JPEG");

            menuDto.setImageBytes(imageBytes);

            menuInfo.add(menuDto);
        }

            return menuInfo;
        }


    public boolean deleteMenu(String menuName) {

        Optional<MenuEntity> menuInfo = menuRepository.findByMenuName(menuName);

        if (menuInfo.isPresent()){
            Integer menuCode = menuInfo.get().getMenuCode();
            menuRepository.deleteById(menuCode);
            return true;
        }else {
            return false;
        }

    }

    public String createAdminToken(LoginDto loginDto){
        return JwtUtil.createJwt(loginDto.getU_id(),secretKey,expiredMs);
    }
}
