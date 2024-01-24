package nong.soon.bae.contorller.all;

import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import nong.soon.bae.bean.AddressDTO;
import nong.soon.bae.bean.UserDetailsDTO;
import nong.soon.bae.bean.UserGradeDTO;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.repository.CustomUser;
import nong.soon.bae.repository.UsersRepository;
import nong.soon.bae.service.MailSendService;
import nong.soon.bae.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	private MailSendService mailService;
	@Autowired
	MemberService memberservice;
	
	@RequestMapping("/form")
	public String loginForm() {
		
		return "all/loginForm";
	}
	
	@RequestMapping("/error")
	public String loginError() {
		
		return "all/loginError";
	}
	
	
	@RequestMapping("/regForm")
	public String regForm() {
		
		return "all/regForm";
	}
	
	@RequestMapping("/reg")
	public String register(UsersDTO users) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		String username = users.getUsername();
		logger.info("===============register================");
		logger.info("==============="+username+"================");
		memberservice.save(users);
		usersRepository.createDetails(username);
		usersRepository.createReviews(username);
		usersRepository.createMypage(username);
		usersRepository.createPayment(username);
		usersRepository.createImages(username);
		
		return "user/welcome";
	}
	@ExceptionHandler(IllegalStateException.class)
    public String handleIllegalStateException(IllegalStateException e, Model model) {
		logger.info("=============error==============");
		model.addAttribute("errorMessage", e.getMessage());
	    return "all/regForm";
    }
	
	@RequestMapping("/welcome")
	public String regSucess(Model model) {
		
		logger.info("===============register success================");
		return "user/welcome";
	}
	
	@RequestMapping("/detailsForm")
	public String detailsForm(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("username", username);
		return "user/detailsForm";
	}
	
	@RequestMapping("/details")
	public String detailPro(MultipartFile image, HttpServletRequest request, Model model, Principal principal, AddressDTO adto, String phone) {
		UserDetailsDTO dto = new UserDetailsDTO();
		String username = principal.getName();
		logger.info("==============="+adto+"================");
		String address = adto.getRoadAddress() + " " + adto.getDetailAddress() + adto.getExtraAddress();
		logger.info("==============="+address+"================");
		String path = request.getServletContext().getRealPath("/resources/file/profile/");
		String filename = image.getOriginalFilename();
		if(!filename.equals("")) {
			String ext = filename.substring(filename.lastIndexOf("."));
			filename = username+"_profile"+ext;
			File copy = new File(path+filename);
			dto.setImage(filename);
			try {
				image.transferTo(copy);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			dto.setImage("default.png");
		}
		dto.setUsername(username);
		dto.setAddress(address);
		dto.setPhone(phone);
		usersRepository.addDetails(dto);	
		
		return "redirect:/user/mypage";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		logger.info("post custom logout");
		return "user/logout";
	}
	
	@RequestMapping("/find")
	public String findIDPW() {
		return"all/find";
	}
	
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheck(String email) {
		System.out.println("이메일 인증 요청이 들어옴!");
		System.out.println("이메일 인증 이메일 : " + email);
		return mailService.joinEmail(email);
	}
	
	@PostMapping("/checkSuccess")
	public String mail(String userEmail1, String userEmail2, Model model) {
		String email = userEmail1+userEmail2;
		int result;
		UsersDTO vo = usersRepository.FindByEmail(email);		
		if(vo == null) {
			result = 0;
			model.addAttribute("result", result);
			model.addAttribute("email", email);
		}else {
			result = 1;
			int site = vo.getRegsite();
			String username = vo.getUsername();
			model.addAttribute("result", result);
			model.addAttribute("username", username);
			model.addAttribute("email", email);
			model.addAttribute("site", site);
		}
		return "user/mail";
	}
	
	@PostMapping("/renamePass")
	public String renamePass(Model model, String username, Principal principal) {
		if(username == null) {
			username = principal.getName();
		}
		model.addAttribute("username", username);
		return "user/renamePass";
	}
	
	@PostMapping("/passPro")
	@ResponseBody
	public String passPro(String password, String username, Principal principal) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		password = passwordEncoder.encode(password);
		usersRepository.changePass(password, username);
		return "success";
	}

}