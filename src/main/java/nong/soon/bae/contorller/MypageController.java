package nong.soon.bae.contorller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/*")
public class MypageController {
	
	@RequestMapping("/main")
	public String main(Principal principal, Model model) {
		String username = principal.getName();
		model.addAttribute("username", username);
		return "/user/mypage";
	}
}
