package LD_Caffe.ld_caffe.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;


@Data
@Entity //db 테이블 의미
@Table(name = "tables")
@Setter
@Getter
public class TablesEntity {

    @Id
    private Integer t_code; //테이블 코드

    @Column
    private Integer t_use; //테이블 쓰고있는지

    @Column
    private Integer t_headcount; //테이블 수용인원


}
