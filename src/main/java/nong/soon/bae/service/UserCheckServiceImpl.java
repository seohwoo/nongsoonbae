package nong.soon.bae.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import nong.soon.bae.bean.UserCheckDTO;

import nong.soon.bae.repository.UserCheckMapper;

@Service
public class UserCheckServiceImpl implements UserCheckService{
	
	@Autowired
	private UserCheckMapper mapper;
	@Autowired
	private HashMap checkMap;
	
	
	
	@Override //일반 유저 목록
	public void userlist(int pageNum, Model model) {
		int pageSize = 10;
	    int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
	    int count = mapper.count();	    
	    List<UserCheckDTO> list = Collections.EMPTY_LIST;
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



	@Override //일반 유저 정지 
	public int userstop(String username) {
		checkMap.clear();
        checkMap.put("username", username);
		return mapper.userstop(checkMap);
	}



	@Override //정지회원정보 저장 
	public int blacklistInsert(String username, String reason) {
		checkMap.clear();
		checkMap.put("username", username);
    	checkMap.put("reason", reason);
		
		return mapper.blacklistInsert(checkMap);
	}



	@Override //정지회원 목록
	public void blacklist(int pageNum, Model model) {
		int pageSize = 10;
	    int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
	    int blackcount = mapper.blackcount();	    
	    List<UserCheckDTO> blacklist = Collections.EMPTY_LIST;
	    if(blackcount > 0) {
	    	checkMap.put("start", startRow);
	    	checkMap.put("end", endRow);
	    	blacklist = mapper.blacklist(checkMap);
	    }	
	    model.addAttribute("blacklist",blacklist);
	    model.addAttribute("blackcount",blackcount);
	    model.addAttribute("pageNum",pageNum);
	    model.addAttribute("pageSize",pageSize);
	    
	    int pageCount = blackcount / pageSize + ( blackcount % pageSize == 0 ? 0 : 1);
		 
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
	@Override //정지회원 복구
	public int reuser(String username) {
		checkMap.clear();
        checkMap.put("username", username);
		return mapper.reuser(checkMap);
	}
	@Override //복구된 회원 블랙리스트에서 삭제 
	public int deleteblacklist(String username) {
		checkMap.clear();
        checkMap.put("username", username);
		return mapper.deleteblacklist(checkMap);
	}

}


