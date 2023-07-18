package LD_Caffe.ld_caffe.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableUpdateDto {

    private Integer seatNum;
    private Boolean seatState;

}
