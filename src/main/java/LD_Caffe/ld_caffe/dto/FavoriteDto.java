package LD_Caffe.ld_caffe.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDto {

    private String userName;
    private String menuCategory;
    private String menuName;
    private Integer menuPrice;
    private byte[] imageBytes;


}
