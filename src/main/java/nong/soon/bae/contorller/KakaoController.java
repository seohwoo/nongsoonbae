package nong.soon.bae.contorller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
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
import nong.soon.bae.bean.KakaoUsersDTO;
import nong.soon.bae.bean.UserGradeDTO;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.repository.CustomUser;
import nong.soon.bae.service.KakaoMemberService;
import nong.soon.bae.service.KakaoService;

@Controller
@AllArgsConstructor
public class KakaoController {
	
	private static final Logger log = LoggerFactory.getLogger(KakaoController.class);
	
	@Autowired
    private KakaoService kakaoService;
	@Autowired
    private KakaoMemberService memberService;

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
        String phone = (String)result.get("phone_number");
        String nickname = (String)result.get("nickname");
        String birth = birthyear+birthday;
        String password = birth+nickname;
        System.out.println(""+username+","+name+","+email+","+gender+","+birth+","+phone+","+nickname+","+password);

        // 분기
        KakaoUsersDTO usersDTO = new KakaoUsersDTO();
        // 일치하는 username 없을 시 회원가입
        System.out.println(memberService.kakaoLogin(username));
        if (memberService.kakaoLogin(username) == null) {
            log.warn("카카오로 회원가입");
            usersDTO.setUsername(username);
            usersDTO.setNickname(nickname);
            usersDTO.setName(name);
            usersDTO.setGender(gender);
            usersDTO.setBirth(birth);
            usersDTO.setPhone(phone);
            usersDTO.setEmail(email);
            usersDTO.setPassword(password);
           // usersDTO.setGrade(100);
            memberService.kakaoJoin(usersDTO);
        }

       
        log.warn("카카오로 로그인");
        KakaoUsersDTO vo = memberService.findByUserId(username);
        String grade = memberService.getgrade(username);
        log.warn("member:: " + vo);
        log.warn("grade : " + grade);
            /*Security Authentication에 붙이는 과정*/
        CustomUser user = new CustomUser(vo);
        log.warn("user : " + user);
        List<GrantedAuthority> roles = new ArrayList<>(1);
//        String roleStr = username.equals("admin") ? "ADMIN" : "MEMBER";
        if(grade=="ADMIN") {
        	roles.add(new SimpleGrantedAuthority("ADMIN"));
        }else {
        	roles.add(new SimpleGrantedAuthority("MEMBER"));
        }
//        roles.add(new SimpleGrantedAuthority(roleStr));
        UserGradeDTO gradeDTO = new UserGradeDTO();
        log.warn("grade : " + grade);
        gradeDTO.setGrade(grade);
        gradeDTO.setUsername(username);
        memberService.setgrade(gradeDTO);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, roles);
        log.warn("auth : " + auth);
        SecurityContextHolder.getContext().setAuthentication(auth);

        /* 로그아웃 처리 시, 사용할 토큰 값 */
        session.setAttribute("kakaoToken", kakaoToken);

        return "redirect:/member/test";

    }

}
