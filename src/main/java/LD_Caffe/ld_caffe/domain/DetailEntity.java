package LD_Caffe.ld_caffe.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="detail")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detailCode;

    @Column(name = "m_code")
    private int menuCode;

    @Column(name = "d_count")
    private int detailCount;

    @Column(name = "o_code")
    private int ordersCode;

    public static DetailEntity toDetailEntity(int menuCode, int menuCount, int orderCode) {
        return DetailEntity.builder()
                .menuCode(menuCode)
                .detailCount(menuCount)
                .ordersCode(orderCode)
                .build();
    }
}
