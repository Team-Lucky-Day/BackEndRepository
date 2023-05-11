package LD_Caffe.ld_caffe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Favorite {
    @Id
    private int f_code;
    private int u_code;
    private int m_code;
}
