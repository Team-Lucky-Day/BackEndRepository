package LD_Caffe.ld_caffe.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class OrderDto {
    private Map<Integer,Integer> orderList; // MenuCode , MenuCount

}
