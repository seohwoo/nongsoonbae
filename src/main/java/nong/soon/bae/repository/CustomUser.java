package nong.soon.bae.repository;

import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import nong.soon.bae.bean.UsersDTO;

@Getter
public class CustomUser extends User {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CustomUser.class);
	private UsersDTO dto;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomUser(UsersDTO dto) {
		super(dto.getUsername(), dto.getPassword(), dto.getGrade().stream().map(grade -> 
		new SimpleGrantedAuthority(grade.getGrade())).collect(Collectors.toList()));
		logger.info("=========CustomUser=========");
		this.dto = dto;
		logger.info("========="+dto+"=========");
	}
}
