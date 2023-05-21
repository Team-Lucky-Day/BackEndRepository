package LD_Caffe.ld_caffe.domain;

import LD_Caffe.ld_caffe.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name =" user")
public class UserEntity {

    @Id
    @Column(name = "u_id")
    private String userId;

    @Column(name = "u_pw")
    private String userPassword;

    @Column(name = "u_name")
    private String userName;

    @Column(name = "u_phone")
    private String userPhone;

    @Column(name = "u_email")
    private String userEmail;

    @Column(name = "c_number")
    private Integer userCardNum;

    public static UserEntity toUserEntity(UserDto userDto){


        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userDto.getUserName());
        userEntity.setUserId(userDto.getUserId());
        userEntity.setUserPassword(userDto.getUserPassword());
        userEntity.setUserEmail(userDto.getUserEmail());
        userEntity.setUserPhone(userDto.getUserPhone());
        userEntity.setUserCardNum(userDto.getCardNum());
        System.out.println("User dto->entity " + userEntity.getUserName());
        return userEntity;
    }


}
