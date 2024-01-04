package nong.soon.bae.contorller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nong.soon.bae.bean.UserDetailsDTO;
import nong.soon.bae.bean.UserGradeDTO;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.repository.CustomUser;
import nong.soon.bae.repository.UsersRepository;
import nong.soon.bae.security.CustomUserDetailsService;

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
		logger.info("===============register================");
		usersRepository.save(users);
		logger.info("===============createDetails================");
		String username = users.getUsername();
		usersRepository.createDetails(username);
		
		logger.warn("회원가입 후 로그인");
        UsersDTO vo = usersRepository.FindByUser(username);
        String grade = usersRepository.GetByAuth(username);
        logger.warn("member:: " + vo);
        logger.warn("grade : " + grade);
        	/*CustomUserDetailsService*/
        CustomUser user = new CustomUser(vo);
        logger.warn("user : " + user);
        
        List<GrantedAuthority> roles = new ArrayList<>(1);
        String roleStr = username.equals("admin") ? "ADMIN" : "MEMBER";
        if(grade=="ADMIN") {
        	roles.add(new SimpleGrantedAuthority("ADMIN"));
        }else {
        	roles.add(new SimpleGrantedAuthority("MEMBER"));
        }
        roles.add(new SimpleGrantedAuthority(roleStr));
        UserGradeDTO gradeDTO = new UserGradeDTO();
        logger.warn("grade : " + grade);
        if(usersRepository.regCheck(username)==null) {
	        gradeDTO.setGrade(grade);
	        gradeDTO.setUsername(username);
	        usersRepository.saveauth(gradeDTO);
        }
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, roles);
        logger.warn("auth : " + auth);
        SecurityContextHolder.getContext().setAuthentication(auth);
		return "redirect:/member/regSuccess";
	}
	
	@RequestMapping("/regSuccess")
	public String regSucess(Model model, Principal principal) {
		String username = principal.getName();
		logger.info("===============register success================");
		logger.info("==============="+username+"================");
		
		model.addAttribute("username", username);
		return "member/welcome";
	}
	
	@RequestMapping("/detailsForm")
	public String detailsForm(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("username", username);
		return "member/detailsForm";
	}
	
	@RequestMapping("/details")
	public String detailPro(Model model, Principal principal, UserDetailsDTO dto) {
		String username = principal.getName();
		logger.info("=========="+username+"==========");
		dto.setUsername(username);
		usersRepository.addDetails(dto);
		return "redirect:/member/detailsForm";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		logger.info("post custom logout");
		return "member/logout";
	}

}
