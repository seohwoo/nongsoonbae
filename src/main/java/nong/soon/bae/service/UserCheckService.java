package nong.soon.bae.service;

import org.springframework.ui.Model;

public interface UserCheckService {
	public void userlist(int pageNum , Model model); //일반 유저 목록
	public String userstop (String username, String reason); //유저 정지하기 
}
