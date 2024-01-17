package nong.soon.bae.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.repository.UserCheckMapper;

@Service
public class UserCheckServiceImpl implements UserCheckService{
	
	@Autowired
	private UserCheckMapper mapper;
	@Autowired
	private HashMap checkMap;
	
	
	
	@Override
	public void userlist(int pageNum, Model model) {
		int pageSize = 10;
	    int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
	    int count = mapper.count();	    
	    List<UsersDTO> list = Collections.EMPTY_LIST;
	    if(count > 0) {
	    	checkMap.put("start", startRow);
	    	checkMap.put("end", endRow);
	    	list = mapper.userlist(checkMap);
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

	}


