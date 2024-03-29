package LD_Caffe.ld_caffe.dto;

import LD_Caffe.ld_caffe.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {


    private String u_name;

    private String u_email;

    private String u_pw;

    private String u_phone;

    private String c_number;

    private Integer cardPassword;

    private Integer cardCvc;

    private String cardDate;

    private String u_id;

}
