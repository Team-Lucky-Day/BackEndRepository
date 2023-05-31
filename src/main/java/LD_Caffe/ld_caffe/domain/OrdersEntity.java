package LD_Caffe.ld_caffe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "orders")
public class OrdersEntity {

    @Id
    @Column(nullable = false)
    private int o_code;

    @Column(nullable = false)
    @CreatedDate
    private Date o_date;

    @Column(nullable = false)
    private String u_id;

    @Column(nullable = false)
    private int t_code;

}
