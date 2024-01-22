package nong.soon.bae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nong.soon.bae.bean.ChatDTO;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.repository.TestMapper;


@Service
public class TestServiceImpl implements TestService{

	
	@Autowired
	private TestMapper mapper;
	
	@Override
	public int count() {
		return mapper.count();
	}

	@Override
	public UsersDTO findUsers(String username) {
		return mapper.findUsers(username);
	}

	@Override
	public List<ChatDTO> userChatList(String username) {
		return mapper.userChatList(username);
	}

	@Override
	public ChatDTO chatInfo(String chatno, String username) {
		return mapper.chatInfo(chatno, username);
	}

	@Override
	public ChatDTO findChat(String chatno, String username) {
		return mapper.findChat(chatno, username);
	}

	@Override
	public void updateNoRead(int cnt, int chatno, String username) {
		mapper.updateNoRead(cnt, chatno, username);
	}

	@Override
	public void zeroNoRead(int chatno, String username) {
		mapper.zeroNoRead(chatno, username);
	}

	@Override
	public ChatDTO findSenduser(String chatno, String username) {
		return mapper.findSenduser(chatno, username);
	}

	@Override
	public void updateJoinCnt(int joincnt, int chatno) {
		mapper.updateJoinCnt(joincnt, chatno);
	}

}
