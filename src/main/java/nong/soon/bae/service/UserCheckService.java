package nong.soon.bae.service;

import org.springframework.ui.Model;

public interface UserCheckService {
	public void userlist(int pageNum , Model model); //�Ϲ� ���� ���
	public String userstop (String username, String reason); //���� �����ϱ� 
}
