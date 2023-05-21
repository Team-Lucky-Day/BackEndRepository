package LD_Caffe.ld_caffe.dto;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {

    /*
    * POST방식으로 오는 파라미터의 name과 일치해야 스프링에서 자동을 매칭해준다.
    * */

    // User Information
    private String userId;
    private String userPassword;
    private String userName;
    private String userPhone;
    private String userEmail;

    // Card Information
    private Integer cardNum;
    private Integer cardPassword;
    private Integer cardCvc;
    private String cardDate;
}
