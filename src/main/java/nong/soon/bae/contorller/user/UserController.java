package nong.soon.bae.contorller.user;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.service.MypageService;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
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
		List<MyPageDTO> user = service.selectLike(username);
		List<MyPageDTO> farmer = service.selectfarmer(username);
		if(user == null) {
			model.addAttribute("likestatus", 0);
		}else {
			model.addAttribute("likestatus", 1);
			service.selectLikeDetail(username, model, listNum);
		}
		if(farmer==null) {
			model.addAttribute("farmerstatus", 0);
		}else {
			model.addAttribute("farmerstatus", 2);
			service.selectFarmerDetail(username, model, listNum);
		}
		return "user/mypage/like";
	}
	
	@PostMapping("deleteLike")
	@ResponseBody
	public String deleteLike(Principal principal, @RequestParam("productnum") String productnum) {
		String username = principal.getName();
        service.deleteLike(username, productnum);
        return "success";
    }
	
	@PostMapping("deleteFarmer")
	@ResponseBody
	public String deleteFarmer(Principal principal, @RequestParam("username") String username) {
		String follow = username;
		username = principal.getName();
		service.deleteFarmer(username, follow);
		return "success";
	}
	
	@RequestMapping("cart")
	public String cart(Principal principal) {
		
		return "user/mypage/cart";
	}
	
	@RequestMapping("deleteCartItem")
	@ResponseBody
	public void deleteCartItem(String productnum, Principal principal) {
		String username = principal.getName();
	}
}
