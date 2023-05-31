package LD_Caffe.ld_caffe.Entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity //db 테이블 의미
public class tables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer t_code; //테이블 코드

    @Column(nullable = false)
    private Integer t_use; //테이블 쓰고있는지

    @Column(nullable = false)
    private Integer t_headcount; //테이블 수용인원


}
