package nong.soon.bae.service;

import org.springframework.ui.Model;

import nong.soon.bae.bean.NoticeBoardDTO;

public interface NoticeService {

	public void list(int pageNum , Model model); //작성글 목록 
	public NoticeBoardDTO readContent(int num); // 글 읽기 
	public int maxNum();
	public int delete(int num);
	
	public void writeInsert (String title, String content,int files);
	public NoticeBoardDTO showNewNotice (); // 최신글 하나 
}
