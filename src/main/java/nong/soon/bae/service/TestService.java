package nong.soon.bae.service;

import java.util.List;

import nong.soon.bae.bean.ChatDTO;
import nong.soon.bae.bean.UsersDTO;

public interface TestService {

	public int count();
	public UsersDTO findUsers(String username);
	public List<ChatDTO> userChatList(String username);
	public ChatDTO chatInfo(String chatno, String username);
	public ChatDTO findChat(String chatno, String username);
	public ChatDTO findSenduser(String chatno, String username);
	public void updateNoRead(int cnt, int chatno, String username);
	public void zeroNoRead(int chatno, String username);
}
   