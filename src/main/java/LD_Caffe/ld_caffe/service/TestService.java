package LD_Caffe.ld_caffe.service;

import LD_Caffe.ld_caffe.domain.CardEntity;
import LD_Caffe.ld_caffe.domain.UserEntity;
import LD_Caffe.ld_caffe.dto.TestDto;
import LD_Caffe.ld_caffe.dto.UserDto;
import LD_Caffe.ld_caffe.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Test;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final CardRepository cardRepository;
//    public void save(TestDto testDto){
//        //1. dto -> entity 변환
//        //2. 변환된 값 repository save 메서드를 통해 db에 저장(DB에는 entity객체로 넘겨야 한다.)
//
//        CardEntity cardEntity = CardEntity.testCardEntity(testDto);
//        System.out.println("CVC넘버를 잘 받아옴. 레퍼지토리 저장 직전 / 값 =>"+cardEntity.getCardCvc());
//        cardRepository.save(cardEntity);
//
//    }


}
