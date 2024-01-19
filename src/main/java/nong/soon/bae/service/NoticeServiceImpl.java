package nong.soon.bae.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import nong.soon.bae.bean.NoticeBoardDTO;
import nong.soon.bae.repository.NoticeMapper;

@Service 
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeMapper mapper;
	
	@Autowired
	private HashMap noticeMap;

	@Override
	public void insert(NoticeBoardDTO dto) { //작성한글 인서트
		mapper.insert(dto);	
	}

	@Override
	public void list(int pageNum, Model model) { //작성글 목록 
		int pageSize = 10;
	    int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
	    int count = mapper.count();
	    List<NoticeBoardDTO> list = Collections.EMPTY_LIST;
	    if(count > 0) {
	    	noticeMap.put("start", startRow);
	    	noticeMap.put("end", endRow);
	    	list = mapper.list(noticeMap);
	    }	
	    model.addAttribute("list",list);
	    model.addAttribute("count",count);
	    model.addAttribute("pageNum",pageNum);
	    model.addAttribute("pageSize",pageSize);
	    
	    int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
		 
        int startPage = (int)(pageNum/10)*10+1;
		int pageBlock=10;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) {
			endPage = pageCount;
        }				
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("startPage",startPage);
        model.addAttribute("pageBlock",pageBlock);
        model.addAttribute("endPage",endPage);
	    
	}

	@Override
	public NoticeBoardDTO readContent(int num) {
		mapper.updateCountUp(num);
		return mapper.readContent(num);
	}


	@Override
	public int maxNum() {
		return mapper.maxNum();
	}

	@Override
	public int delete(int num) {
		return mapper.delete(num);
	}

	@Override
	public NoticeBoardDTO showNewNotice() {
		return mapper.showNewNotice();
	}


	
}
