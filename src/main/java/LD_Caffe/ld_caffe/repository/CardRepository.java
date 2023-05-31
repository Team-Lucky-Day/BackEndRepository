package LD_Caffe.ld_caffe.repository;

import LD_Caffe.ld_caffe.domain.CardEntity;
import LD_Caffe.ld_caffe.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, String> {



    List<CardEntity> findByCardNumber(String cardNumber);


}
