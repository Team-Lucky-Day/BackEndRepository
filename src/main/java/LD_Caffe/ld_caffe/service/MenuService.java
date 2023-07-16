package LD_Caffe.ld_caffe.service;

import LD_Caffe.ld_caffe.domain.MenuEntity;
import LD_Caffe.ld_caffe.dto.FavoriteDto;
import LD_Caffe.ld_caffe.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuEntity> getList(String category) {
        return menuRepository.findBymenuCategory(category);
    }

    public List<FavoriteDto> getAllFavMenu(List<Integer> favoriteMenuCode, String userName) throws IOException {

        List<FavoriteDto> result = new ArrayList<>();

        for (Integer code: favoriteMenuCode){
            FavoriteDto favoriteDto = new FavoriteDto();

            Optional<MenuEntity> menuInfo = menuRepository.findById(code);
            if (menuInfo.isPresent()){
                favoriteDto.setUserName(userName);
                favoriteDto.setMenuCategory((menuInfo.get()).getMenuCategory());
                favoriteDto.setMenuName(menuInfo.get().getMenuName());
                favoriteDto.setMenuPrice(menuInfo.get().getMenuPrice());

                String imagePath = menuInfo.get().getMenuImagePath();
                File file = new File(imagePath);
                byte[] imageBytes = Files.readAllBytes((file.toPath()));

                favoriteDto.setImageBytes(imageBytes);
            }else {
                System.out.println("DTO생성 오류");
            }
            result.add(favoriteDto);
        }

        return result;
    }
}
