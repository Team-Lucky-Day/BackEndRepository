package LD_Caffe.ld_caffe.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private String userId;
    private String userPw;
}
