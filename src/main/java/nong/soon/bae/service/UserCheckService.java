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
	
	public void showCate(Model model); //ī�װ� �з��ϱ�
	public int maxNum();
	
	public int insertNewCate (int num, String addCate);
	int addCateFile(String addCate, String realname);
	

	public void showSubCate (Model model, int cate1Select);
	public void showSelectCate1 (Model model, int cate1Select);
	
	
}
