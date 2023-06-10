package LD_Caffe.ld_caffe.service;

import LD_Caffe.ld_caffe.domain.UserEntity;
import LD_Caffe.ld_caffe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    //유저 이름 가져오기
    public ArrayList<String> getUserNames(){

        List<UserEntity> userInfo = userRepository.findAll();
        ArrayList<String> userNames = new ArrayList<>();

        for (UserEntity i : userInfo){
            userNames.add(i.getUserName());
        }

        return userNames;
    }


    public Integer deleteUser(String userId){

        userRepository.deleteById(userId);

        return 1;
    }

}
