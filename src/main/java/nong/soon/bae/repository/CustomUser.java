package nong.soon.bae.repository;

import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import nong.soon.bae.bean.KakaoUsersDTO;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.security.CustomLoginHandler;

@Getter
public class CustomUser extends User {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CustomUser.class);
	private UsersDTO dto;
	private KakaoUsersDTO kdto;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomUser(UsersDTO dto) {
		super(dto.getUsername(), dto.getPassword(), dto.getGradenames().stream().map(gradename -> {
			if (gradename.getGradenum() == dto.getGrade()) {
				return new SimpleGrantedAuthority(gradename.getGradename());
			} else {
				return null;
			}
		}).collect(Collectors.toList()));
		logger.info("=========CustomUser=========");
		this.dto = dto;
		logger.info("========="+dto+"=========");
	}
	
	public CustomUser(KakaoUsersDTO kdto) {
		super(kdto.getUsername(),kdto.getPassword(),kdto.getGradenames().stream().map(auth -> new SimpleGrantedAuthority(auth.getGradename())).collect(Collectors.toList()));
		this.kdto = kdto;
	}

}
