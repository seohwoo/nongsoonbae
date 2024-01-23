package nong.soon.bae.contorller.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.service.MypageService;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	@Autowired
	MypageService service;
	
	@RequestMapping("mypage")
	public String mypage(Principal principal, Model model) {
		String username = principal.getName();
		model.addAttribute("username", username);
		return "user/mypage/mypage";
	}
	
	@RequestMapping("home")
	public String home(Principal principal, Model model) {
		String username = principal.getName();
		model.addAttribute("username", username);
		return "user/mypage/home";
	}
	
	@RequestMapping("like")
	public String like(Model model, Principal principal, @RequestParam(value="pageNum",required = true , defaultValue="1" )int listNum) {
		String username = principal.getName();
		MyPageDTO user = service.selectLike(username);
		String productnum = user.getProductnum();
		service.selectLikeDetail(username, productnum, model, listNum);
		return "user/mypage/like";
	}
	
	@RequestMapping("cart")
public String cart(Principal principal) {
		
		return "user/mypage/cart";
	}
}
