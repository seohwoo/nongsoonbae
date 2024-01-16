package nong.soon.bae.repository;

import nong.soon.bae.bean.UsersDTO;

public interface TestMapper {
	
	public int count();
	public UsersDTO findUsers(String username);

}
