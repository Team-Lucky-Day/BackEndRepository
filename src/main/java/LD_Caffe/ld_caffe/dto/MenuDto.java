package LD_Caffe.ld_caffe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuDto {

    private String category;
    private String name;
    private String content;
    private Integer price;
    private String imagePath;
    private int menuCode;

    private byte[] imageBytes;
    private String imageType;

}