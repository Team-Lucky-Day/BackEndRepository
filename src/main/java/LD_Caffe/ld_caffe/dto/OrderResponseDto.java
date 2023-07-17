package LD_Caffe.ld_caffe.dto;


import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponseDto {

    private int orderCode;

    private Date orderDate;

    private List<DetailResponseDto> details;

}
