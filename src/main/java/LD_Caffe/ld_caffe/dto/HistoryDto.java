package LD_Caffe.ld_caffe.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HistoryDto {
    private int h_price;
    private String u_id;
    private Date h_date;
}
