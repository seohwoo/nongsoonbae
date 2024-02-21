package nong.soon.bae.contorller.user;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.PaymentDTO;
import nong.soon.bae.repository.UsersRepository;
import nong.soon.bae.service.MypageService;
import nong.soon.bae.service.PayService;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	MypageService service;
	@Autowired
	private PayService payService;
	@Autowired
	private SimpleDateFormat simpleDateFormat;
	
	@Autowired
	UsersRepository user;
	
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
			model.addAttribute("farmerstatus", 1);
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
	public String cart(Principal principal, Model model) {
		String username = principal.getName();
		List<MyPageDTO> cart = service.selectcart(username);
		
		if(cart == null) {
			model.addAttribute("cartstatus", 0);
		}else {
			model.addAttribute("cartstatus", 1);
			service.selectMyCart(username, model);
			model.addAttribute("isMembership", 0);
		}
		return "user/mypage/cart";
	}
	
	@RequestMapping("deleteCartItem")
	@ResponseBody
	public String deleteCartItem(String optionnum, Principal principal) {
		String username = principal.getName();
		service.deleteCart(username, optionnum);
		return "success";
	}
	
	@RequestMapping("buylist")
	public String buylist(Principal principal, Model model) {
		String username = principal.getName();
		List<PaymentDTO> buy = service.selectPay(username);
		if(buy==null) {
			model.addAttribute("buystatus", 0);
		}else {
			model.addAttribute("buystatus", 1);
			service.selectPayDetail(username, model);
		}
		return "/user/mypage/buylist";
	}
	
	@RequestMapping("delete")
	public String deleteUser(Principal principal, Model model) {
		String username = principal.getName();
		model.addAttribute("username", username);
		return "/user/mypage/delete";
	}
	
	@RequestMapping("deletePro")
	public String deletePro(String username, RedirectAttributes redirectAttrs) {
		int result = user.UserDelete(username);
		if(result>0) {
			redirectAttrs.addFlashAttribute("msg", "성공적으로 회원정보를 삭제했습니다.");
			SecurityContextHolder.clearContext();
		}
		else {
			redirectAttrs.addFlashAttribute("msg", "회원정보삭제에 실패했습니다.");
		}
		return "redirect:/user/logout";
	}
	
	@RequestMapping("membership")
	public String membership(Principal principal, Model model) {
		String username = principal.getName();
		boolean ismem = false;
		if(payService.isMembership(username).getGrade().get(0).getGrade().equals("ROLE_MEMBERSHIP")) {
			ismem = true;
			Date lastMembershipPayDate = payService.lastMembershipPayDate(username).get(0).getOrderdate();
			String[] arDate = simpleDateFormat.format(lastMembershipPayDate).split("/");
			int month = Integer.parseInt(arDate[1])+1 > 12 ? 1 : Integer.parseInt(arDate[1])+1;
			String nextPayDate = arDate[0] + "년 " + month + "월 " + arDate[2] + "일"; 
			model.addAttribute("nextPayDate", nextPayDate);
		}
		model.addAttribute("ismem", ismem);
		model.addAttribute("username", username);
		model.addAttribute("isMembership", 1);
		return "/user/mypage/membership";
	}
	@RequestMapping("quitMembership")
	public String quitMembership(Principal principal) {
		payService.userQuitMembership(principal.getName());
		return "redirect:/product/productMain";
	}
}
