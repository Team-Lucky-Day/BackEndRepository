package LD_Caffe.ld_caffe.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="favorite")
public class FavoriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_code")
    private Integer favoriteCode;

    @Column(name = "u_code")
    private String userCode;

    @Column(name = "m_code")
    private Integer menuCode;

}
