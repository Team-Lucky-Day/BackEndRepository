package LD_Caffe.ld_caffe.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class ChartDto {
    HashMap<String,Category> monthCategory;

}

@Getter
@Setter
class Category {
    int coffee;
    int dessert;
    int beverage;
}
