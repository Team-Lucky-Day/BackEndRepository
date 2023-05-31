package LD_Caffe.ld_caffe;

import LD_Caffe.ld_caffe.dto.UserDto;
import LD_Caffe.ld_caffe.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LdCaffeApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void userSignUp() {
		UserDto userDTO = new UserDto();
		userDTO.setU_name("test1");
		userDTO.setU_id("testID1");
		userDTO.setU_pw("testPW1");
		userDTO.setU_email("testEmail@naver.com");
		userDTO.setU_phone("01099999999");
		userDTO.setC_number(2992929);
		userService.saveUser(userDTO);
	}

	@Test
	void userDelete(){
		userService.deleteUser("testID1");
	}

}
