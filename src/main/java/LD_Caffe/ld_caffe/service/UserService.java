package LD_Caffe.ld_caffe.service;

import LD_Caffe.ld_caffe.entity.User;
import LD_Caffe.ld_caffe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAllUser(){  // Repository 의 findAll() 메서드 호출
        return userRepository.findAll();
    }

    public Optional<User> findUserById(String u_id){  // Repository 의 findById 로 ID값으로 유저 조회
        return userRepository.findById(u_id);
    }
}
