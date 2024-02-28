package nong.soon.bae.service;

import org.springframework.ui.Model;

import nong.soon.bae.bean.NoticeBoardDTO;

public interface NoticeService {

	public void list(int pageNum , Model model); //�ۼ��� ��� 
	public NoticeBoardDTO readContent(int num); // �� �б� 
	public int maxNum();
	public int delete(int num);
	
	public void writeInsert (String title, String content,int files);
	public NoticeBoardDTO showNewNotice (); // �ֽű� �ϳ� 
}
