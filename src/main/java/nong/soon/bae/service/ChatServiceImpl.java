package nong.soon.bae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nong.soon.bae.bean.ChatDTO;
import nong.soon.bae.repository.ChatMapper;

@Service
public class ChatServiceImpl implements ChatService{

	
	@Autowired
	private ChatMapper mapper;
	
	
	
	
	@Override
	public List<ChatDTO> userChatList(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChatDTO chatInfo(String chatno, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChatDTO findChat(String chatno, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChatDTO findSenduser(String chatno, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateNoRead(int cnt, int chatno, String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zeroNoRead(int chatno, String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateJoinCnt(int joincnt, int chatno) {
		// TODO Auto-generated method stub
		
	}
	
}
