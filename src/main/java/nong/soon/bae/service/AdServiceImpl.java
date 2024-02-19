package nong.soon.bae.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import nong.soon.bae.bean.AdDTO;
import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.NoticeBoardDTO;
import nong.soon.bae.repository.AdMapper;


@Service
public class AdServiceImpl implements AdService {

	@Autowired
	private AdMapper mapper;
	@Autowired
	private HashMap adMap;
	
	@Override
	public void myproduct(Model model,String username) {
		List<AllProductDTO> myproduct = Collections.EMPTY_LIST;
		myproduct = mapper.myproduct(username);
		model.addAttribute("myproduct",myproduct);
		
	}

	@Override
	public void myAd(Model model, String username) {
		List<AdDTO> myAdList = Collections.EMPTY_LIST;
		myAdList = mapper.myAd(username);
		int myAdCnt = mapper.myAdCnt(username);
		model.addAttribute("myAdList",myAdList);
		model.addAttribute("myAdCnt",myAdCnt);
		
	}
	
	
	@Override
	public List<AdDTO> checkAd(String adSelect, String username) {
		adMap.clear();
		adMap.put("adSelect", adSelect);
		adMap.put("username", username);
		return mapper.checkAd(adMap);
	}

	
	
	@Override
	public void submitAd(String username, String adSelect, int daySelect, int price) {
		adMap.clear();
		adMap.put("username", username);
		adMap.put("adSelect", adSelect);
		adMap.put("daySelect", daySelect);
		adMap.put("price", price);
		mapper.submitAd(adMap);
		
	}

	@Override
	public void adList(Model model,int pageNum) {
		int pageSize = 10;
	    int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
	    int submitCnt = mapper.submitCnt();
	    List<AdDTO> adList = Collections.EMPTY_LIST;
	    if(submitCnt > 0) {
	    	adMap.put("start", startRow);
	    	adMap.put("end", endRow);
	    	adList = mapper.adList(adMap);
	    }	
	    model.addAttribute("adList",adList);
	    model.addAttribute("submitCnt",submitCnt);
	    model.addAttribute("pageNum",pageNum);
	    model.addAttribute("pageSize",pageSize);
	    
	    int pageCount = submitCnt / pageSize + ( submitCnt % pageSize == 0 ? 0 : 1);
		 
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
	public void adStart(String productnum,int days,int num) {
		adMap.clear();
		adMap.put("productnum", productnum);
		adMap.put("days", days);
		adMap.put("num", num);
		mapper.adStart(adMap);
	}
	
	@Override
	public void adNo(String productnum, String username,int num) {
		adMap.clear();
		adMap.put("productnum", productnum);		
		adMap.put("username", username);
		adMap.put("num", num);
		mapper.adNo(adMap);
	}

	
	
	@Override
	public void updateStatus(String productnum) {
		adMap.clear();
		adMap.put("productnum", productnum);
		mapper.updateStatus(adMap);
		
	}

	@Override
	public void adEndSoon(Model model,int pageNum ) {
		int pageSize = 10;
	    int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
	    int endSoonCnt = mapper.endSoonCnt();
		List<AdDTO> endSoonList = Collections.EMPTY_LIST;
	    if(endSoonCnt > 0) {
	    	adMap.put("start", startRow);
	    	adMap.put("end", endRow);
	    	endSoonList = mapper.adEndSoon(adMap);
	    }	
	    model.addAttribute("endSoonList",endSoonList);
	    model.addAttribute("endSoonCnt",endSoonCnt);
	    model.addAttribute("pageNum",pageNum);
	    model.addAttribute("pageSize",pageSize);
	    
	    int pageCount = endSoonCnt / pageSize + ( endSoonCnt % pageSize == 0 ? 0 : 1);
		 
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
	public void adEnd(int num,String username, String productnum) {
		adMap.clear();
		adMap.put("num", num);
		adMap.put("username", username);
		adMap.put("productnum", productnum);
		mapper.adEnd(adMap);
		
	}

	@Override
	public void reUpdateStatus(String username, String productnum) {
		adMap.clear();
		adMap.put("username", username);
		adMap.put("productnum", productnum);
		mapper.reUpdateStatus(adMap);
	}

	@Override
	public void adIngList(int pageNum,Model model) {
		int pageSize = 10;
	    int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
	    int adIngCnt = mapper.adIngCnt();
	    List<AdDTO> list = Collections.EMPTY_LIST;
	    if(adIngCnt > 0) {
	    	adMap.put("start", startRow);
	    	adMap.put("end", endRow);
	    	list = mapper.adIngList(adMap);
	    }	
	    model.addAttribute("list",list);
	    model.addAttribute("adIngCnt",adIngCnt);
	    model.addAttribute("pageNum",pageNum);
	    model.addAttribute("pageSize",pageSize);
	    
	    int pageCount = adIngCnt / pageSize + ( adIngCnt % pageSize == 0 ? 0 : 1);
		 
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
	public void adEndList(int pageNum, Model model) {
		int pageSize = 10;
	    int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
	    int adEndCnt = mapper.adEndCnt();
	    List<AdDTO> endList = Collections.EMPTY_LIST;
	    if(adEndCnt > 0) {
	    	adMap.put("start", startRow);
	    	adMap.put("end", endRow);
	    	endList = mapper.adEndList(adMap);
	    }	
	    model.addAttribute("endList",endList);
	    model.addAttribute("adEndCnt",adEndCnt);
	    model.addAttribute("pageNum",pageNum);
	    model.addAttribute("pageSize",pageSize);
	    
	    int pageCount = adEndCnt / pageSize + ( adEndCnt % pageSize == 0 ? 0 : 1);
		 
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


	
