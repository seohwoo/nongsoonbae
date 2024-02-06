package nong.soon.bae.contorller.user;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nong.soon.bae.bean.AdDTO;
import nong.soon.bae.service.AdService;


@Controller
public class AdController {
	private static final Logger log = LoggerFactory.getLogger(AdController.class);
	
	@Autowired
	private AdService service; 

	@RequestMapping("/product/adMain")
	public String adMain(Model model,Principal principal) {
		String username = principal.getName();	
		model.addAttribute("username",username);
		return "product/ad/adMain";
	}
	
	@RequestMapping("/product/myAd") //내가 신청한 광고 확인
	public String myAd(Model model,Principal principal) {
		String username = principal.getName();	
		service.myAd(model,username);
		return "product/ad/myAd";
	}
	
	@RequestMapping("/product/adForm") //광고 신청 폼
	public String adFrom(Model model,Principal principal,
			@RequestParam("userId") String username) {
		service.myproduct(model,username);
		return "product/ad/adForm";
	}
	
	@RequestMapping("/product/adFormPro")
	public String adFormPro(Model model, Principal principal,
	                        @RequestParam("adSelect") String adSelect,
	                        @RequestParam("daysSelect") String daysSelect,
	                        @RequestParam("price") String price,
	                        RedirectAttributes redirectAttributes,
	                        HttpServletRequest request) {
	    String username = principal.getName();
	    List<AdDTO> check = service.checkAd(adSelect,username);
	    if (check == null || check.isEmpty()) { // 광고가 존재하지 않는 경우
	        if (adSelect != null && daysSelect != null && price != null) {
	            service.submitAd(username, adSelect, Integer.parseInt(daysSelect), Integer.parseInt(price));
	            redirectAttributes.addFlashAttribute("submitStatus", 1);
	        } else {
	            redirectAttributes.addFlashAttribute("submitStatus", 0); // 필수 값 중 하나라도 null인 경우
	        }
	    } else { // 광고가 이미 존재하는 경우 (검토중 OR 광고중)
	        redirectAttributes.addFlashAttribute("submitStatus", 2);
	    }
	    
	    return "redirect:/product/myAd";
	}
	
	@RequestMapping("/admin/adList") //들어온 광고 목록들 확인하기 
	public String adList(Model model) { 
		Date today = new Date();
        model.addAttribute("today", today);
		service.adList(model);
		return "admin/ad/adList";
	}
	
	@RequestMapping("/admin/adPro") //광고 승인
	public String adPro(Model model,
			@RequestParam("startDate") String startDateStr,
			@RequestParam("days") int days,
			@RequestParam("productnum") String productnum,
			RedirectAttributes redirectAttributes) {
        if(productnum != null) {
        	service.adStart(productnum,days);
        	service.updateStatus(productnum);
        	redirectAttributes.addFlashAttribute("adStatus", 1);
	    } else {
	        redirectAttributes.addFlashAttribute("adStatus", 0);
	    }
	    
		return "redirect:/admin/adList";
	}
	
	@RequestMapping("/admin/adNoPro") //광고 거절
	public String adNoPro(Model model,
			@RequestParam("productnum") String productnum,
			@RequestParam("username") String username,
			@RequestParam("num") int num,
			RedirectAttributes redirectAttributes) {
		if(productnum != null) {
			service.adNo(productnum,username,num);
			redirectAttributes.addFlashAttribute("noAdStatus", 1);
	    } else {
	        redirectAttributes.addFlashAttribute("noAdStatus", 0);
	    }
		
		return "redirect:/admin/adList";
	}
	
	
	@RequestMapping("/admin/adEndSoon")//오늘 날짜로 끝나는 광고 목록
	public String adEndSoon(Model model) {
		service.adEndSoon(model);
		return "admin/ad/adEndSoon";
	}
	
	
	@RequestMapping("/admin/adEndPro") //광고 내리기
	public String adEndPro(Model model,
			@RequestParam("username") String username,
			@RequestParam("productnum") String productnum,
			@RequestParam("num") int num,
			RedirectAttributes redirectAttributes) {
		if(username != null && productnum != null) {
			service.adEnd(num,username,productnum);
			service.reUpdateStatus(username,productnum);
			redirectAttributes.addFlashAttribute("endAdStatus", 1);
	    } else {
	        redirectAttributes.addFlashAttribute("endAdStatus", 0);
	    }
		
		return "redirect:/admin/adEndSoon";
	}
	
	@RequestMapping("/admin/adIng")//현재 광고 중인 상품
	public String adIng(Model model,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum) {
		service.adIngList(pageNum,model);
		return "admin/ad/adIng";
	}
	@RequestMapping("/admin/adEnd")//현재 광고 중인 상품
	public String adEnd(Model model,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum) {
		service.adEndList(pageNum,model);
		return "admin/ad/adEnd";
	}
	
}
