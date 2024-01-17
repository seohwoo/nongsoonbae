package nong.soon.bae.service;

import org.springframework.ui.Model;

public interface UserCheckService {
	public void userlist(int pageNum , Model model); //일반 유저 목록
	
}
