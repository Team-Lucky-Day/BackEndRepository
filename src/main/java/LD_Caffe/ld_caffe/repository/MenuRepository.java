package LD_Caffe.ld_caffe.repository;

import LD_Caffe.ld_caffe.domain.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {


    List<MenuEntity> findBymenuCategory(String category);

    Optional<MenuEntity> findByMenuName(String menuName);
}
