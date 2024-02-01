package nong.soon.bae.contorller.user;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@RequestMapping("/product/adForm")
	public String adFrom(Model model,Principal principal,
			@RequestParam("userId") String username) {
		service.myproduct(model,username);
		
		return "product/ad/adForm";
	}
	
	@RequestMapping("/product/adFormPro")
	public String adFormPro(Model model,Principal principal,
			@RequestParam("userId") String username) {
		
		
		
		return "redirect:/product/ad/adMain";
	}
	
	
	
}
