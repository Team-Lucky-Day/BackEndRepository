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

    public User toEntity(){
        return User.builder()
                .u_id(this.getU_id())
                .u_pw(this.getU_pw())
                .u_name(this.getU_name())
                .u_email(this.getU_email())
                .u_phone(this.getU_phone())
                .c_number(this.getC_number())
                .build();
    }
}
