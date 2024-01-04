package nong.soon.bae.contorller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import nong.soon.bae.bean.UserGradeDTO;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.repository.CustomUser;
import nong.soon.bae.repository.UsersRepository;
import nong.soon.bae.security.CustomLoginHandler;
import nong.soon.bae.service.KakaoService;

@Controller
@AllArgsConstructor
public class KakaoController {
	
	private static final Logger log = LoggerFactory.getLogger(KakaoController.class);
	
	@Autowired
    private KakaoService kakaoService;
	@Autowired
    private UsersRepository memberService;

    @SuppressWarnings("null")
	@RequestMapping(value = "/login/oauth2/code/kakao", method = RequestMethod.GET)
    public String redirectkakao(@RequestParam String code, HttpSession session) throws IOException {
        System.out.println("code:: " + code);

        // 접속토큰 get
        String kakaoToken = kakaoService.getReturnAccessToken(code);

        // 접속자 정보 get
        Map<String, Object> result = kakaoService.getUserInfo(kakaoToken);
        log.info("result:: " + result);
        String username = (String) result.get("id");
        String name = (String) result.get("name");
        String email = (String) result.get("email");
        String gender = (String) result.get("gender");
        String birthyear = (String)result.get("birthyear");
        String birthday = (String)result.get("birthday");
        String birth = birthyear.substring(2)+birthday;
        String password = email;
        System.out.println(""+username+","+name+","+email+","+gender+","+birth+","+password);

        // 분기
        UsersDTO usersDTO = new UsersDTO();
        // 일치하는 username 없을 시 회원가입
        System.out.println(memberService.login(username));
        if (memberService.login(username) == null) {
            log.warn("카카오로 회원가입");
            usersDTO.setUsername(username);
            usersDTO.setName(name);
            usersDTO.setBirth(birth);
            usersDTO.setEmail(email);
            usersDTO.setPassword(password);
            if(gender=="male") {
            	usersDTO.setGender(1);
            }else {
            	usersDTO.setGender(2);
            }
            memberService.save(usersDTO);
        }

       
        log.warn("카카오로 로그인");
        UsersDTO vo = memberService.FindByUser(username);
        String grade = memberService.GetByAuth(username);
        log.warn("member:: " + vo);
        log.warn("grade : " + grade);
            /*Security Authentication에 붙이는 과정*/
        	/*CustomUserDetailsService*/
        CustomUser user = new CustomUser(vo);
        log.warn("user : " + user);
        
        List<GrantedAuthority> roles = new ArrayList<>(1);
        String roleStr = username.equals("admin") ? "ADMIN" : "MEMBER";
        if(grade=="ADMIN") {
        	roles.add(new SimpleGrantedAuthority("ADMIN"));
        }else {
        	roles.add(new SimpleGrantedAuthority("MEMBER"));
        }
        roles.add(new SimpleGrantedAuthority(roleStr));
        UserGradeDTO gradeDTO = new UserGradeDTO();
        log.warn("grade : " + grade);
        if(memberService.regCheck(username)==null) {
	        gradeDTO.setGrade(grade);
	        gradeDTO.setUsername(username);
	        memberService.saveauth(gradeDTO);
        }
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, roles);
        log.warn("auth : " + auth);
        SecurityContextHolder.getContext().setAuthentication(auth);
        
        /* 로그아웃 처리 시, 사용할 토큰 값 */
        session.setAttribute("kakaoToken", kakaoToken);

        return "redirect:/member/test";

    }

}
