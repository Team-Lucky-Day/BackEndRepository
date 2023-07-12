package LD_Caffe.ld_caffe.domain;

import LD_Caffe.ld_caffe.dto.HistoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int h_code;

    @Column(name="u_id")
    private String userId;

    @Column
    private int h_price;

    @Column
    private Date h_date;

    public static HistoryEntity toHistoryEntity(HistoryDto historyDto) {
        return HistoryEntity.builder()
                .userId(historyDto.getU_id())
                .h_price(historyDto.getH_price())
                .h_date(historyDto.getH_date())
                .build();
    }

}
