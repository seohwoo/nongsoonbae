package nong.soon.bae.service;

import org.springframework.ui.Model;

import nong.soon.bae.bean.NoticeBoardDTO;

public interface NoticeService {

	public void insert(NoticeBoardDTO dto); //�ۼ��� �μ�Ʈ 
	public void list(int pageNum , Model model); //�ۼ��� ��� 
	public NoticeBoardDTO readContent(int num); // �� �б� 
	public int maxNum();
	public int delete(int num);
	
}
