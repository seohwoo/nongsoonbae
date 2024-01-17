package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import nong.soon.bae.bean.UsersDTO;

public interface UserCheckMapper {
	public int count();
	public List<UsersDTO> userlist(HashMap map);
}
