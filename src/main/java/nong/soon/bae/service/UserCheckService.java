package nong.soon.bae.service;

import org.springframework.ui.Model;

public interface UserCheckService {
	public void userlist(int pageNum , Model model); //�Ϲ� ���� ���
	public int userstop(String username); //���� �����ϱ�
	public void findUser(Model model, int pageNum, String userSearch); //�˻� 	
	public int blacklistInsert(String username, String reason); //���� ȸ�� ������Ʈ ���̺� ���� 
	public void blacklist(int pageNum , Model model);//����ȸ�����
	public int reuser(String username); //����ȸ�������ϱ�
	public int deleteblacklist (String username); //������Ʈ���� �����ϱ� 
	public void blackFindUser(Model model, int pageNum, String userSearch);//����ȸ���˻�
}
