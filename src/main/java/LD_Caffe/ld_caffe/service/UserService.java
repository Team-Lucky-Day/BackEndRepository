package LD_Caffe.ld_caffe.service;

import LD_Caffe.ld_caffe.domain.UserEntity;
import LD_Caffe.ld_caffe.dto.LoginDto;
import LD_Caffe.ld_caffe.dto.UserDto;
import LD_Caffe.ld_caffe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    public Boolean isIdExsits(LoginDto loginDto) {  // ID 중복확인 메서드
        if(userRepository.findById(loginDto.getU_id()).isPresent()){
            return true;
        }else{
            return false;
        }

    } // 존재하면 true 존재하지 않으면 false

    public ResponseEntity<String> saveUser(UserDto userDto){  // 회원가입 메서드
        LoginDto loginDto = LoginDto.builder().u_id(userDto.getU_id()).u_pw(userDto.getU_pw()).build();
        if (isIdExsits(loginDto)){
            return ResponseEntity.badRequest().build();
        }else{
            UserEntity user = UserEntity.toEntity(userDto);
            userRepository.save(user);
            return ResponseEntity.ok("회원가입이 완료되었습니다.");
        }
    }

    public void deleteUser(String userId){
        userRepository.delete(userRepository.findById(userId).get());
    }

    //Login 메서드
    public boolean userLogin(LoginDto loginDto){
        Optional<UserEntity> user = userRepository.findById(loginDto.getU_id());
        if (user.isPresent()){  // 로그인 성공
            return user.get().getUserPassword().equals(loginDto.getU_pw());
        }else{  // 로그인 실패
            return false;
        }
    }


}





