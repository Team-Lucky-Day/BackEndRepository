package LD_Caffe.ld_caffe.dto;

import LD_Caffe.ld_caffe.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String u_id;

    private String u_pw;

    private String u_name;

    private String u_phone;

    private String u_email;

    private Integer c_number;

    public User toEntity(UserDTO userDTO){
        return User.builder()
                .u_id(userDTO.getU_id())
                .u_pw(userDTO.getU_pw())
                .u_name(userDTO.getU_name())
                .u_email(userDTO.getU_email())
                .u_phone(userDTO.getU_phone())
                .c_number(userDTO.getC_number())
                .build();
    }
}
