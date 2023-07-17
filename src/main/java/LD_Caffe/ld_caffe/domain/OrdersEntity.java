package LD_Caffe.ld_caffe.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "orders")
@Builder
public class OrdersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int o_code;

    @Column(nullable = false)
    private Date o_date;

    @Column(nullable = false, name="u_id")
    private String userId;

    @Column(nullable = false)
    private int t_code;

    public static OrdersEntity toOrdersEntity(String userId) {
        return OrdersEntity.builder()
                .userId(userId)
                .o_date(Date.valueOf(LocalDate.now()))
                .build();

    }

}
