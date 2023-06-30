package LD_Caffe.ld_caffe.repository;

import LD_Caffe.ld_caffe.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByuserName(String userName);


    @Query(
            value = "select u.*,c.c_pw,c.c_cvc,c.c_date from user u, card c where u.c_number = c.c_number",
            nativeQuery = true) //nativeQuery조건이 없으면 오류가 난다.
    List<UserEntity> getAllUserInfo();

//
//    @Query("select u.* from user u where ")
//    List<UserEntity> getuserTable;
}
