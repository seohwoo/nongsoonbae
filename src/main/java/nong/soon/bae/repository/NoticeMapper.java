package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import nong.soon.bae.bean.NoticeBoardDTO;

public interface NoticeMapper {

	public void insert (NoticeBoardDTO dto); //작성글 인서트
	public int count(); //작성글 총 갯수 
	public List<NoticeBoardDTO> list (HashMap map); //작성글 목록
	public void updateCountUp(int num); //조회수 증가하기 
	public NoticeBoardDTO readContent(int num);
	public int maxNum(); //가장 최신글 번호  

	public int delete(int num); //글 삭제하기
	public NoticeBoardDTO showNewNotice(); //가장 최신글 

}
