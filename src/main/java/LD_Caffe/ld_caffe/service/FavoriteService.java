package LD_Caffe.ld_caffe.service;

import LD_Caffe.ld_caffe.domain.FavoriteEntity;
import LD_Caffe.ld_caffe.dto.FavoriteDto;
import LD_Caffe.ld_caffe.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final MenuService menuService;

    public List<FavoriteDto> getFavoriteMenu(String userCode, String userName) throws IOException {

        Optional<List<FavoriteEntity>> favoriteInfo = favoriteRepository.findByUserCode(userCode);
        if (favoriteInfo.isPresent()){
            List<Integer> favoriteMenuCode = new ArrayList<>();

            for (FavoriteEntity info : favoriteInfo.get()){
                favoriteMenuCode.add(info.getMenuCode());

            }

            List<FavoriteDto> result = menuService.getAllFavMenu(favoriteMenuCode, userName);

            return result;

        }else {
            System.out.println("FavoriteService 오류");
            return null;

        }

    }

    public boolean enrollFavorite(Integer menuCode,
                                  String userCode){

        try{
            FavoriteEntity newFavMenu = new FavoriteEntity();
            newFavMenu.setMenuCode(menuCode);
            newFavMenu.setUserCode(userCode);
            favoriteRepository.save(newFavMenu);

            return true;
        }catch (Exception error){
            System.out.println("ERROR : "+error);
            return  false;
        }


    }
}
