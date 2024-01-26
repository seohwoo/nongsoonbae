package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import nong.soon.bae.bean.ChatDTO;
import nong.soon.bae.bean.UsersDTO;

public interface ChatMapper {
	public int userChatCnt(HashMap<String, String> map);
	public List<ChatDTO> userChatList(HashMap<String, String> map);
	public ChatDTO chatInfo(HashMap<String, String> map);
	public ChatDTO findSenduser(HashMap<String, String> map);
	public void updateNoRead(@Param("cnt") int cnt, @Param("chatno") int chatno, @Param("username") String username);
	public void zeroNoRead(HashMap<String, String> map);
	public void updateJoinCnt(@Param("joincnt") int joincnt, @Param("chatno") int chatno);
	public UsersDTO getAdminUser();
	public int adminChatCnt(HashMap<String, String> map);
	public void insertQnaToMe(HashMap<String, String> map);
	public void insertQnaToSend(HashMap<String, String> map);
	public ChatDTO adminChatInfo(HashMap<String, String> map);
}





