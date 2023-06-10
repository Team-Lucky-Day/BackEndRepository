package LD_Caffe.ld_caffe.domain;

import LD_Caffe.ld_caffe.dto.UserDto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name ="user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private String userCardNum;


    public static UserEntity toEntity(UserDto userDto){
        return UserEntity.builder()
                .userId(userDto.getU_id())
                .userPassword(userDto.getU_pw())
                .userCardNum(userDto.getC_number())
                .userEmail(userDto.getU_email())
                .userPhone(userDto.getU_phone())
                .userName(userDto.getU_name())
                .build();
    }


}
