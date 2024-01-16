package nong.soon.bae.contorller.all;

import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
		logger.info("===============form================");
		return "all/loginForm";
	}
	
	@RequestMapping("/error")
	public String loginError() {
		logger.info("===============error================");
		return "all/loginError";
	}
	
	
	@RequestMapping("/regForm")
	public String regForm() {
		logger.info("===============reg================");
		return "all/regForm";
	}
	
	@GetMapping("/checkUsernameAvailability")
    public ResponseEntity<Boolean> checkUsernameAvailability(@RequestParam String username) {
        boolean isAvailable = memberservice.isUsernameAvailable(username);
        return ResponseEntity.ok(isAvailable);
    }
	
	@RequestMapping("/reg")
	public String register(UsersDTO users) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		String username = users.getUsername();
		logger.info("===============register================");
		usersRepository.save(users);
		logger.info("===============createDetails================");
		
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
        String roleStr = grade.equals("admin") ? "ADMIN" : "MEMBER";
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
		return "redirect:/user/regSuccess";
	}
	
	@RequestMapping("/regSuccess")
	public String regSucess(Model model, Principal principal) {
		String username = principal.getName();
		logger.info("===============register success================");
		logger.info("==============="+username+"================");
		
		model.addAttribute("username", username);
		return "user/welcome";
	}
	
	@RequestMapping("/detailsForm")
	public String detailsForm(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("username", username);
		return "user/detailsForm";
	}
	
	@RequestMapping("/details")
	public String detailPro(MultipartFile image, HttpServletRequest request, Model model, Principal principal, String address, String phone) {
		UserDetailsDTO dto = new UserDetailsDTO();
		String username = principal.getName();
		
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
		logger.info("=========="+username+"==========");
		logger.info("=========="+address+"==========");
		logger.info("=========="+phone+"==========");
		logger.info("=========="+filename+"==========");
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
	public String renamePass(Model model, String username) {
		model.addAttribute("username", username);
		return "user/renamePass";
	}
	
	@PostMapping("/passPro")
	@ResponseBody
	public String passPro(String password, String username) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		password = passwordEncoder.encode(password);
		logger.info("=============controll============");
		logger.info("============="+password+"============");
		logger.info("============="+username+"============");
		usersRepository.changePass(password, username);
		return "success";
	}

}