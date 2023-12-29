package nong.soon.bae.repository;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import nong.soon.bae.bean.UsersDTO;

// 상속받고있는 조상클래스가 기본생성자가 없는경우 자손에서 생성자를 구현해야함
@Getter
public class CustomUser extends User {
	
	private UsersDTO dto;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}


	

	
}
