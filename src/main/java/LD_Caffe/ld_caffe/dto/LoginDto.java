package LD_Caffe.ld_caffe.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private String u_id;
    private String u_pw;
}
