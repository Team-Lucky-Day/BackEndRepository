package LD_Caffe.ld_caffe.service;

import LD_Caffe.ld_caffe.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public boolean isCardVaild(String cardNum) {  // Card 가 DB에 존재하는지 조회
        System.out.println("cardNum = " + cardNum);
        return !cardRepository.findByCardNumber(cardNum).isEmpty();
    }
}
