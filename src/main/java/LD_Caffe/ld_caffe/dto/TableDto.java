package LD_Caffe.ld_caffe.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableDto {

   private Integer tableNumber;
   private Boolean useTable;
   private Integer headCount;

}
