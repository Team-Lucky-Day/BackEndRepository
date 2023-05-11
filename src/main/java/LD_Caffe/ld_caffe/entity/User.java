package LD_Caffe.ld_caffe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    @Id
    private String u_id;
    private String u_pw;
    private String u_name;
    private int u_phone;
    private String u_email;
    private int c_number;
}
