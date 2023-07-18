package LD_Caffe.ld_caffe.controller;

import LD_Caffe.ld_caffe.dto.TableUpdateDto;
import LD_Caffe.ld_caffe.service.TablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/table")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TablesController {


    private final TablesService tablesService;

    @Value("${ADMIN.PW}")
    private String ADMIN_PW;

    private boolean amIAdmin(Authentication authentication){  // ADMIN 계정 확인 메서드
        return authentication.getName().equals("ADMIN"); // ADMIN 이 맞다면 TRUE 아니면 FALSE
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> changeSeatState(@RequestBody TableUpdateDto tableUpdateDto,
                                                   Authentication authentication){

        if (!amIAdmin(authentication)) { // ADMIN 이 아니라면 여기서 블락
            return ResponseEntity.status(403).build();
        }

        Integer seatNumber = tableUpdateDto.getSeatNum();
        Boolean seatState = tableUpdateDto.getSeatState();
        System.out.println("변경하고자 하는 자리 번호 : " + seatNumber);
        System.out.println("변경할 자리의 현자 상태 : " + seatState);

        Boolean result = tablesService.changeState(seatNumber, seatState);
        System.out.println("테이블 사용상태 변경 성공여부 >>>" + result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
