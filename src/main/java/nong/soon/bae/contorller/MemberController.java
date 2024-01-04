package nong.soon.bae.contorller;

import java.security.Principal;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nong.soon.bae.bean.UserGradeDTO;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.repository.CustomUser;
import nong.soon.bae.repository.UsersRepository;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	UsersRepository usersRepository;
	
	@RequestMapping("/form")
	public String loginForm() {
		logger.info("===============form================");
		return "member/loginForm";
	}
	
	@RequestMapping("/error")
	public String loginError() {
		logger.info("===============error================");
		return "member/loginError";
	}
	
	@RequestMapping("/test")
	public String loginTest(Model model, Principal principal, UsersDTO dto) {
	/*	CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UsersDTO users = new UsersDTO();
		users.setUsername(customUser.getUsername());	*/	
		String username = principal.getName();
		logger.info("===============login success================");
	//	logger.info("==============="+users+"================");
	//	String username = users.getUsername();
		logger.info("==============="+username+"================");
		
		model.addAttribute("username", username);
		return "member/loginTest";
	}
	
	@RequestMapping("/regForm")
	public String regForm() {
		logger.info("===============reg================");
		return "member/regForm";
	}
	
	@RequestMapping("/reg")
	public String register(UsersDTO users) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		usersRepository.save(users);
		return "redirect:/member/form";
	}
	
	@RequestMapping("/regSuccess")
	public String regSucess(Model model, Principal principal) {
		String username = principal.getName();
		logger.info("===============register success================");
		logger.info("==============="+username+"================");
		
		model.addAttribute("username", username);
		return "member/welcome";
	}
	
	@RequestMapping("/detailForm")
	public String detailForm(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("username", username);
		return "member/detailForm";
	}
	
	@RequestMapping("/details")
	public String detailPro(Model model, Principal principal) {
		String username = principal.getName();
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		logger.info("post custom logout");
		return "member/logout";
	}

}
