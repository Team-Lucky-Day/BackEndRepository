package LD_Caffe.ld_caffe.repository;

import LD_Caffe.ld_caffe.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {
}
