package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

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
}
