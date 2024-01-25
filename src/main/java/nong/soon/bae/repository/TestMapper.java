package nong.soon.bae.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import nong.soon.bae.bean.ChatDTO;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.UsersDTO;

public interface TestMapper {
	
	public int count();
	public UsersDTO findUsers(String username);
	public List<ChatDTO> userChatList(String username);
	public ChatDTO chatInfo(@Param("chatno") String chatno, @Param("username") String username);
	public ChatDTO findChat(@Param("chatno") String chatno, @Param("username") String username);
	public ChatDTO findSenduser(@Param("chatno") String chatno, @Param("username") String username);
	public void updateNoRead(@Param("cnt") int cnt, @Param("chatno") int chatno, @Param("username") String username);
	public void zeroNoRead(@Param("chatno") int chatno, @Param("username") String username);
	public void updateJoinCnt(@Param("joincnt") int joincnt, @Param("chatno") int chatno);
	public int findAddCartCnt(String username);
	public List<String> findAddCartSeller(String username);
	public List<MyPageDTO> findAddCart(@Param("username") String username, @Param("seller") String seller);
}
