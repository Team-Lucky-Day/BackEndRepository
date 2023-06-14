package LD_Caffe.ld_caffe.domain;

import LD_Caffe.ld_caffe.dto.MenuDto;
import lombok.*;

import javax.persistence.*;

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


    public static MenuEntity toMenuEntity(MenuDto menuDto){
        return MenuEntity.builder()
                .menuCategory(menuDto.getCategory())
                .menuName(menuDto.getName())
                .menuContents(menuDto.getContent())
                .menuPrice(menuDto.getPrice())
                .build();
    }
}
