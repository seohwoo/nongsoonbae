package nong.soon.bae.contorller.user;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	@RequestMapping("mypage")
	public String mypage(Principal principal, Model model) {
		String username = principal.getName();
		model.addAttribute("username", username);
		return "user/mypage/mypage";
	}
	
	@RequestMapping("home")
	public String home(String username,  Model model) {
		model.addAttribute("username", username);
		return "user/mypage/home";
	}
	
	@RequestMapping("like")
	public String like(Principal principal) {
		
		return "user/mypage/like";
	}
}
