package nong.soon.bae.service;

import org.springframework.ui.Model;

public interface UserCheckService {
	public void userlist(int pageNum , Model model); //일반 유저 목록
	public int userstop(String username); //유저 정지하기 
	public int blacklistInsert(String username, String reason); //정지 회원 블랙리스트 테이블에 저장 
	public void blacklist(int pageNum , Model model);//정지회원목록
	public int reuser(String username); //정지회원복구하기
	public int deleteblacklist (String username); //블랙리스트에서 제거하기 

}
