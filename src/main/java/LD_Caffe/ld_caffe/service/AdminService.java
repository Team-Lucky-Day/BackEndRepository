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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public List<UserEntity> getUserInfoList(){

        return userRepository.findAll();
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


    public void deleteImageFile(String imagePath){
        try {
            System.out.println("기존 이미지 경로 : " + imagePath);
            Path filePath = Paths.get(imagePath);
            Files.delete(filePath);
            System.out.println("기존 이미지가 삭제되었습니다.");
        } catch (NoSuchFileException e) {
            e.printStackTrace();
            System.out.println("삭제하고자 하는 이미지 파일이 없습니다. 이미지 경로=>" + imagePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("삭제하려는 이미지 파일 삭제 중 오류 발생. 이미지 경로 => " + imagePath);
        }
    }

    public ArrayList<Object> updateMenu(String menuName,
                              String name,
                              Integer price,
                              String content,
                              String imagePath){

        System.out.println("adminService에서 메뉴 이름으로 정보 찾기 시작");
        Optional<MenuEntity> menuInfo = menuRepository.findByMenuName(menuName);
        System.out.println("adminService에서 메뉴 이름으로 정보 찾기 성공");

        String preImagePath = menuInfo.get().getMenuImagePath();
        ArrayList<Object> result = new ArrayList<>();
        if (menuInfo.isPresent()){

            try{
                MenuEntity menuInfoToUpdate = menuInfo.get();
                menuInfoToUpdate.setMenuName(name);
                menuInfoToUpdate.setMenuPrice(price);
                menuInfoToUpdate.setMenuContents(content);

                if(imagePath != null){
                    menuInfoToUpdate.setMenuImagePath(imagePath);
                }

                menuRepository.save(menuInfoToUpdate);

                result.add(true);
                result.add(preImagePath);
                return result;
            }catch(Exception error){
                System.err.println(error.getMessage());

                result.add(false);
                return result;
            }

        }else {
            System.out.println("존재하지 않는 데이터입니다.");
            result.add(false);
            return result;
        }
    }

}
