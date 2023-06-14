package LD_Caffe.ld_caffe.repository;

import LD_Caffe.ld_caffe.domain.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {


}
