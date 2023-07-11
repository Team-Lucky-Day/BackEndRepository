package LD_Caffe.ld_caffe.domain;

import LD_Caffe.ld_caffe.dto.TestDto;
import LD_Caffe.ld_caffe.dto.UserDto;
import lombok.*;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name ="card")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    // <<<TEST>>>
//    public static CardEntity testCardEntity(TestDto testDto){
//
//
//        CardEntity testEntity = new CardEntity();
//        testEntity.setCardNumber(testDto.getCardNum());
//        testEntity.setCardPassword(testDto.getCardPassword());
//        testEntity.setCardCvc(testDto.getCardCvc());
//        testEntity.setCardDate(testDto.getCardDate());
//        System.out.println(testEntity.getCardCvc());
//        return testEntity;
//    }
}
