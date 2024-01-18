package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import nong.soon.bae.bean.NoticeBoardDTO;

public interface NoticeMapper {

	public void insert (NoticeBoardDTO dto); //�ۼ��� �μ�Ʈ
	public int count(); //�ۼ��� �� ���� 
	public List<NoticeBoardDTO> list (HashMap map); //�ۼ��� ���
	public void updateCountUp(int num); //��ȸ�� �����ϱ� 
	public NoticeBoardDTO readContent(int num);
	public int maxNum(); //���� �ֽű� ��ȣ  

	public int delete(int num); //�� �����ϱ�
	public NoticeBoardDTO showNewNotice(); //���� �ֽű� 

}
