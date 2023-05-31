package LD_Caffe.ld_caffe.service;

import LD_Caffe.ld_caffe.domain.CardEntity;
import LD_Caffe.ld_caffe.domain.UserEntity;
import LD_Caffe.ld_caffe.dto.UserDto;
import LD_Caffe.ld_caffe.repository.CardRepository;
import LD_Caffe.ld_caffe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
//@ToString
public class UserInfoService {

    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    //DB에 값 저장
    public void save(UserDto userDto){
        //1. dto -> entity 변환
        //2. 변환된 값 repository save 메서드를 통해 db에 저장(DB에는 entity객체로 넘겨야 한다.)

        UserEntity userEntity = UserEntity.toEntity(userDto);
        System.out.println("User entity로 변환되고 저장준비 완료" + userEntity.getUserName());
        CardEntity cardEntity = CardEntity.toCardEntity(userDto);
        System.out.println("Card entity로 변환되고 저장준비 완료" + cardEntity);
        userRepository.save(userEntity);
        cardRepository.save(cardEntity);

    }


    // 모든 유저정보 가져오기
    public List<UserEntity> getAllUser(){

        return userRepository.getAllUserInfo();
    }

    // 유저 정보 삭제
    public void DeleteUser(String userId){
        userRepository.deleteById(userId);
    }

    public void deleteCard(String cardNum){
        cardRepository.deleteById(cardNum);
    }


}
