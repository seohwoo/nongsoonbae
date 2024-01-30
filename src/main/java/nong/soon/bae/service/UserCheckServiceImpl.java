package nong.soon.bae.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ShopListDTO;
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

	@Override
	public void findUser(Model model, int pageNum, String userSearch) {
		int pageSize = 10;
		int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
		String keyword = "%" + userSearch + "%";
		checkMap.put("keyword", keyword);
		int cnt = mapper.searchUserCnt(keyword);
		List<UserCheckDTO> searchlist = Collections.EMPTY_LIST;
		if(cnt > 0) {
			checkMap.put("start", startRow);
	    	checkMap.put("end", endRow);
	    	checkMap.put("keyword", keyword);
			searchlist = mapper.serchUserlist(checkMap);
		}
		model.addAttribute("userSearch", userSearch);
		model.addAttribute("searchCnt", cnt);
		model.addAttribute("searchlist", searchlist);
	}
		
	

	@Override //일반 유저 정지 
	public int userstop(String username) {
		checkMap.clear();
        checkMap.put("username", username);
		return mapper.userstop(checkMap);
	}
	
	@Override //정지 대상 회원 상점 조회하기 
	public List<ShopListDTO> findShop(String username) {
		return mapper.findshop(username);			
	}


	@Override //shoplist 정지
	public int shopstop(String username) {
		checkMap.clear();
        checkMap.put("username", username);
		return mapper.shopstop(checkMap);
	}
	
	@Override //allproduct 정지 
	public int allstop(String username) {
		checkMap.clear();
        checkMap.put("username", username);
		return mapper.allstop(checkMap);
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
	
	@Override
	public int reshop(String username) {
		return mapper.reshop(username);
	}

	@Override
	public int reall(String username) {
		return mapper.reall(username);
	}
	
	
	@Override //복구된 회원 블랙리스트에서 삭제 
	public int deleteblacklist(String username) {
		checkMap.clear();
        checkMap.put("username", username);
		return mapper.deleteblacklist(checkMap);
	}

	@Override
	public void blackFindUser(Model model, int pageNum, String userSearch) {
		int pageSize = 10;
		int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
		String keyword = "%" + userSearch + "%";
		checkMap.put("keyword", keyword);
		int blackcnt = mapper.blacksearchUserCnt(keyword);
		List<UserCheckDTO> blacksearchlist = Collections.EMPTY_LIST;
		if(blackcnt > 0) {
			checkMap.put("start", startRow);
	    	checkMap.put("end", endRow);
	    	checkMap.put("keyword", keyword);
			blacksearchlist = mapper.blackserchUserlist(checkMap);
		}
		model.addAttribute("userSearch", userSearch);
		model.addAttribute("blacksearchCnt", blackcnt);
		model.addAttribute("blacksearchlist", blacksearchlist);
	}

	@Override
	public void showCate(Model model) {
		List<ProductCategoryDTO> catelist = Collections.EMPTY_LIST;
		catelist = mapper.showCate();
		model.addAttribute("catelist",catelist);
		
		}

	@Override
	public int maxNum() {
		return mapper.maxNum();
	}

	@Override
	public int insertNewCate(int cateNum, String addCate) {
		checkMap.clear();
        checkMap.put("cateNum", cateNum);
        checkMap.put("addCate", addCate);
		return mapper.insertNewCate(checkMap);
		
		}

	@Override
	public int addCateFile(String addCate, String realname) {
		checkMap.put("realname", realname);
        checkMap.put("addCate", addCate);
		return mapper.addCateFile(checkMap);
	}

	@Override
	public void showSubCate(Model model, int cate1Select) {
		List<ProductCategoryDTO> subcatelist = Collections.EMPTY_LIST;
		subcatelist = mapper.showSubCate(cate1Select);
		model.addAttribute("subcatelist",subcatelist);
	}

	

	@Override
	public int subMaxNum(int cate1Select) {
		return mapper.subMaxNum(cate1Select);
	}


	@Override
	public void showDetailCate(Model model, int cate1Select) {
		List<ProductCategoryDTO> detailCateList = Collections.EMPTY_LIST;
		detailCateList=mapper.showDetailCate(cate1Select);
		model.addAttribute("detailCateList",detailCateList);
		}

	

	@Override
	public int subDetailMaxNum(int maxNum, int cate1Select) {
		checkMap.clear();
		checkMap.put("cate1Select",cate1Select);
		checkMap.put("maxNum",maxNum);
		return mapper.subDetailMaxNum(checkMap);
	}

	@Override
	public int insertDetailCate(int cate1Select, int subMaxNum, int detailNum, String addDetail) {
		checkMap.clear();
		checkMap.put("cate1Select",cate1Select);
		checkMap.put("subMaxNum",subMaxNum);
		checkMap.put("detailNum",detailNum);
		checkMap.put("addDetail",addDetail);
	
		return mapper.insertDetailCate(checkMap);
	}

	@Override
	public int addDetailFile(String realname, String addDetail) {
		checkMap.clear();
		checkMap.put("realname",realname);
		checkMap.put("addDetail",addDetail);
		return mapper.addDetailFile(checkMap);
		}

	@Override
	public void showEtcCate(Model model, int cate1Select, int subMaxNum) {
		List<ProductCategoryDTO> etcList = Collections.EMPTY_LIST;
		checkMap.clear();
		checkMap.put("cate1Select",cate1Select);
		checkMap.put("subMaxNum",subMaxNum);
		etcList = mapper.showEtcCate(checkMap);
		model.addAttribute("etcList",etcList);
		}

	@Override
	public String findEtcName(int cate1Select, int subMaxNum) {
		checkMap.clear();
		checkMap.put("cate1Select",cate1Select);
		checkMap.put("subMaxNum",subMaxNum);
		return mapper.findEtcName(checkMap);
	}

	@Override
	public int updateCateName(String newCateName, int cate1Select, int subMaxNum) {
		checkMap.clear();
		checkMap.put("newCateName",newCateName);
		checkMap.put("cate1Select",cate1Select);
		checkMap.put("subMaxNum",subMaxNum);
		
		return mapper.updateCateName(checkMap);
	}

	@Override
	public int updateEtcCate(int cate1Select, int etcNum, String etcName) {
		checkMap.clear();
		checkMap.put("cate1Select",cate1Select);
		checkMap.put("etcNum",etcNum);
		checkMap.put("etcName",etcName);
		return mapper.updateEtcCate(checkMap);
	}

	@Override
	public int deleteDetailCate(int detailNum, String addDetail) {
		checkMap.clear();
		checkMap.put("detailNum",detailNum);
		checkMap.put("addDetail",addDetail);
		return mapper.deleteDetailCate(checkMap);
	}

	@Override
	public int deleteCate(int cateNum, String addCate) {
		checkMap.clear();
		checkMap.put("cateNum",cateNum);
		checkMap.put("addCate",addCate);
		return mapper.deleteCate(checkMap);
	}

	

	
	
	

	
	}



	




