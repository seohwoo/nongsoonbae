package nong.soon.bae.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import nong.soon.bae.bean.UserGradeDTO;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.contorller.MemberController;
import nong.soon.bae.repository.CustomUser;
import nong.soon.bae.repository.UsersRepository;



public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private UsersRepository mapper;

	@SuppressWarnings("null")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("=========CustomUserDetailsService=========");
		UsersDTO dto = mapper.login(username);
		String grade = mapper.GetByAuth(username);
		List<GrantedAuthority> roles = new ArrayList<>(1);
		if(grade=="ADMIN") {
        	roles.add(new SimpleGrantedAuthority("ADMIN"));
        }else {
        	roles.add(new SimpleGrantedAuthority("MEMBER"));
        }
		Authentication auth = new UsernamePasswordAuthenticationToken(username, dto.getPassword(), roles);
		logger.warn("auth : " + auth);
        SecurityContextHolder.getContext().setAuthentication(auth);
        if(mapper.regCheck(username)==null) {
        	UserGradeDTO gradeDTO = new UserGradeDTO();
            gradeDTO.setGrade(grade);
            gradeDTO.setUsername(username);
        	mapper.saveauth(gradeDTO);
        }
		logger.info(""+dto);
		return dto == null ? null : new CustomUser(dto);
	}
}

