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
	
	//~~������� ȸ�������ϱ�
	
	public void showCate(Model model); //ī�װ� �з��ϱ�
	public int maxNum(); //��з��� ���� ū �� �̱�
	
	public int insertNewCate (int cateNum, String addCate); //��з� ī�װ� �μ�Ʈ
	public int addCateFile(String addCate, String realname); //��з� ���� ���� �μ�Ʈ
	public int deleteCate (int cateNum, String addCate); //���� ���� ���н� ��з� ������ ����
	
	public void showSubCate (Model model, int cate1Select); //�ߺз� ��� 
	public int subMaxNum(int cate1Select); //�ش��ϴ� ��з��� ���� ū �ߺз���
	
	public void showDetailCate(Model model,int cate1Select); //�Һз� �׸� ���
	public int subDetailMaxNum(int maxNum, int cate1Select); 
	public int deleteDetailCate (int detailNum, String addDetail); //���� ���� ���н� �Һз� ������ ����
	public int insertDetailCate(int cate1Select,int subMaxNum, int detailNum, String addDetail);//�Һз� �׸� �߰��ϱ�
	public int addDetailFile (String realname, String addDetail); //�Һз� ���� �߰�
	 
	
	public void showEtcCate (Model model,int cate1Select, int subMaxNum); //��Ÿ�׸�
	public String findEtcName(int cate1Select,int subMaxNum); //��Ÿ�з� �̸� �̱�
	
	public int updateCateName (String newCateName,int cate1Select , int subMaxNum); //�ߺз� ���� ������Ʈ
	public int updateEtcCate (int cate1Select, int etcNum, String etcName); //��Ÿ�׸� ���� ���� 
	
	
}
