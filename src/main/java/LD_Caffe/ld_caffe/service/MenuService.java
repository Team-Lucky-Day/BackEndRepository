package LD_Caffe.ld_caffe.service;


import LD_Caffe.ld_caffe.domain.MenuEntity;
import LD_Caffe.ld_caffe.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<MenuEntity> getAllMenus() {
        return menuRepository.findAll();
    }
    //메뉴정보를 보두 가져오기 하였음 예시로 유저 정보를 가저오는 것과 같은 메커니즘을 쓸려고 하였는데 id 대신에 이름 으로찾는 방법을 쓸려
    //하였지만 중간에 고치다가 날라가버림...
    //이상하게 GPT한테 물어보면 애가 Repository로 연결하는데 아닌것같아서 Controller에서는 Service로 바꿔놨음

    public byte[] loadImage(String imageName) throws IOException {
        String imagePath = "/path/to/images/folder/" + imageName;
        File imageFile = new File(imagePath);

        if (!imageFile.exists()) {
            throw new FileNotFoundException("Image not found: " + imageName);
        }

        return Files.readAllBytes(imageFile.toPath());
    }
    //위 path~~~는 자신의 image가 저장되어있는 경로를 집어 넣으면됨, 현재 이미지 1개는 출력이 되지만 나머지도 같은 이미지로
    //출력되는 문제가 발생해서 현재 확인 필요함


}
