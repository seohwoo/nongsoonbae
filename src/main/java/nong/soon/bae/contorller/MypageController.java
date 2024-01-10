package nong.soon.bae.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/*")
public class MypageController {
	
	@PostMapping("/main")
	public String main() {
		
		return "/mypage/main";
	}
}
