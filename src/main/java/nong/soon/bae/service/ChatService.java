package nong.soon.bae.service;


import org.springframework.ui.Model;

import nong.soon.bae.bean.ChatDTO;


public interface ChatService {

	
	public void userChatList(Model model, String username);
	public void chatInfo(Model model,String chatno, String username);
	public int updateNoRead(int cnt, int chatno, String username);
	public int updateJoinCnt(int joincnt, int chatno, int isjoin);
	public ChatDTO createAdminRoom(String username);
	public ChatDTO createRoom(String username, String follow);
}


