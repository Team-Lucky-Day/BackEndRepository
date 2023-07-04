package LD_Caffe.ld_caffe.service;

import LD_Caffe.ld_caffe.domain.MenuEntity;
import LD_Caffe.ld_caffe.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuEntity> getList(String category) {
        return menuRepository.findBymenuCategory(category);
    }
}
