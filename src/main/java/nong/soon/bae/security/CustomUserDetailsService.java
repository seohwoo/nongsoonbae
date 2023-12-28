package nong.soon.bae.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.contorller.MemberController;
import nong.soon.bae.repository.CustomUser;
import nong.soon.bae.repository.SecurityMapper;



public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private SecurityMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info(username);
		UsersDTO dto = mapper.login(username);
		logger.info(""+dto);
		return dto == null ? null : new CustomUser(dto);
	}
	
	
}

