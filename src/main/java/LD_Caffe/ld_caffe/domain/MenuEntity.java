package LD_Caffe.ld_caffe.domain;

import LD_Caffe.ld_caffe.dto.MenuDto;
import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="menu")
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_code")
    private Integer menuCode;

    @Column(name = "m_name")
    private String menuName;

    @Column(name = "m_contents")
    private String menuContents;

    @Column(name = "m_price")
    private Integer menuPrice;

    @Column(name = "m_category")
    private String menuCategory;

    @Column(name = "m_imgPath")
    private String menuImagePath;


    public static MenuEntity toMenuEntity(MenuDto menuDto){
        return MenuEntity.builder()
                .menuCategory(menuDto.getCategory())
                .menuName(menuDto.getName())
                .menuContents(menuDto.getContent())
                .menuPrice(menuDto.getPrice())
                .menuImagePath(menuDto.getImagePath())
                .build();
    }
}
