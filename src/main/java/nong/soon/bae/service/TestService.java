package nong.soon.bae.service;

import nong.soon.bae.bean.UsersDTO;

public interface TestService {

	public int count();
	public UsersDTO findUsers(String username);
}
   