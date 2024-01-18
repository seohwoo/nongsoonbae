package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import nong.soon.bae.bean.UserCheckDTO;


public interface UserCheckMapper {
	public int count(); //일반회원수
	public List<UserCheckDTO> userlist(HashMap map);
	int userstop(HashMap map);
	int blacklistInsert(HashMap map);
	public int blackcount(); //정지 회원 수 
	public List<UserCheckDTO> blacklist(HashMap map);
	int reuser(HashMap map); //정지 회원 복구하기 
	int deleteblacklist(HashMap map); //블랙리스트에서 제거
}
