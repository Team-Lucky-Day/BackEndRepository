package LD_Caffe.ld_caffe.repository;

import LD_Caffe.ld_caffe.domain.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Integer> {


    Optional<List<FavoriteEntity>> findByUserCode(String userCode);
}
