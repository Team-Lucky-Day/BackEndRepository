package LD_Caffe.ld_caffe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Orders {
    @Id
    private int o_code;
    private Date o_date;
    private int u_code;
    private int t_code;

}
