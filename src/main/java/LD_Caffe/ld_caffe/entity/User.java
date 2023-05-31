package LD_Caffe.ld_caffe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    @Id
    @Column(nullable = false)
    private String u_id;
    @Column(nullable = false)
    private String u_pw;
    @Column(nullable = false)
    private String u_name;
    @Column(nullable = false)
    private String u_phone;
    @Column(nullable = false)
    private String u_email;
    @Column
    private String c_number;
}
