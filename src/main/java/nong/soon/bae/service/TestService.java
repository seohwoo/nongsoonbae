package nong.soon.bae.service;

import java.util.List;

import nong.soon.bae.bean.ChatDTO;
import nong.soon.bae.bean.UsersDTO;

public interface TestService {

	public int count();
	public UsersDTO findUsers(String username);
	public List<ChatDTO> userChatList(String username);
}
   