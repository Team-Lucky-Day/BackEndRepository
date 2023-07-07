package LD_Caffe.ld_caffe.domain;

import LD_Caffe.ld_caffe.dto.TestDto;
import LD_Caffe.ld_caffe.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name =" card")
@Builder
public class CardEntity {

    @Id
    @Column(name = "c_number")
    private String cardNumber;

    @Column(name = "c_pw")
    private Integer cardPassword;

    @Column(name = "c_cvc")
    private Integer cardCvc;

    @Column(name = "c_date")
    private String cardDate;

    public static CardEntity toCardEntity(UserDto userDto){
        System.out.println("Card dto->entity ");
        return CardEntity.builder()
                .cardNumber(userDto.getC_number())
                .cardCvc(userDto.getCardCvc())
                .cardDate(userDto.getCardDate())
                .cardPassword(userDto.getCardPassword())
                .build();
    }

}
