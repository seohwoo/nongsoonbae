package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.UserCheckDTO;


public interface UserCheckMapper {
	public int count(); //�Ϲ�ȸ����
	public List<UserCheckDTO> userlist(HashMap map);
	int userstop(HashMap map);
	int blacklistInsert(HashMap map);
	public int blackcount(); //���� ȸ�� �� 
	public List<UserCheckDTO> blacklist(HashMap map);
	int reuser(HashMap map); //���� ȸ�� �����ϱ� 
	int deleteblacklist(HashMap map); //������Ʈ���� ����
	public int searchUserCnt(String keyword); //�˻� ��� �� 
	public List<UserCheckDTO> serchUserlist(HashMap checkMap); //�˻����
	public int blacksearchUserCnt(String keyword); //���� �˻�
	public List<UserCheckDTO> blackserchUserlist(HashMap checkMap); //���� �˻� ���
	
	public List<ProductCategoryDTO> showCate(); //ī�װ� �߰��ϱ� 
	public int maxNum();
	
	int insertNewCate(HashMap map); //��з� ī�װ�
	int addCateFile(HashMap map);
	public List<ProductCategoryDTO> showSubCate(int cate1Select);
	public String showSelectCate1(int cate1Select);
}
