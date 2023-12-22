package nong.soon.bae.repository;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import nong.soon.bae.bean.UsersDTO;

// ��ӹް��ִ� ����Ŭ������ �⺻�����ڰ� ���°�� �ڼտ��� �����ڸ� �����ؾ���
@Getter
public class CustomUser extends User {
	
	private UsersDTO dto;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}


	

	
}
