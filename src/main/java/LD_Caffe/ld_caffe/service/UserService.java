package LD_Caffe.ld_caffe.service;

import LD_Caffe.ld_caffe.domain.UserEntity;
import LD_Caffe.ld_caffe.dto.UserDto;
import LD_Caffe.ld_caffe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserEntity> findAllUser(){  // Repository 의 findAll() 메서드 호출
        return userRepository.findAll();
    }

    public Optional<UserEntity> findUserById(String u_id){  // Repository 의 findById 로 ID값으로 유저 조회
        return userRepository.findById(u_id);
    }

    public void saveUser(UserDto userDto){  // 유저 저장 메서드
        UserEntity user = UserEntity.toEntity(userDto);
        userRepository.save(user);
    }

    public void deleteUser(String userId){
        userRepository.delete(userRepository.findById(userId).get());
    }
}
