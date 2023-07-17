package LD_Caffe.ld_caffe.dto;

import LD_Caffe.ld_caffe.domain.DetailEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DetailResponseDto {

    private String menuName;

    private int detailCount;

    private int ordersCode;

    private int detailPrice;

    public DetailResponseDto toDetailResponseDto(DetailEntity entity){
        return DetailResponseDto.builder()
                .detailCount(getDetailCount())
                .ordersCode(getOrdersCode())
                .build();
    }

}
