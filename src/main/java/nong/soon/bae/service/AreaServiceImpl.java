package nong.soon.bae.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductListDTO;
import nong.soon.bae.repository.AreaMapper;
import nong.soon.bae.repository.MainMapper;

@Service
public class AreaServiceImpl implements AreaService{
	@Autowired
	private AreaMapper mapper;
	@Autowired
	private HashMap<String, String> categoryMap;
	@Autowired
	private HashMap<String, String> seasonCategoryMap;
	@Autowired
	private MainMapper mainMapper;
	@Autowired
	private ArrayList<ProductListDTO> productList;
	
	@Override
	public List<AreaDTO> areaMenu(Model model) {
	
		return mapper.arealist();
	}	
	@Override
	public void allproductlist(Model model,String sort,int pageNum) { //전체항목
		int pageSize = 10;
	    int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
		int allCnt = mapper.allCnt();
		List<AllProductDTO> allprocuctList = Collections.EMPTY_LIST ;
		if(allCnt >0 ) {
			 if ("readcnt".equals(sort)) {		
				 	categoryMap.clear();
				 	categoryMap.put("start", String.valueOf(startRow));
				 	categoryMap.put("end", String.valueOf(endRow));
				 	allprocuctList = mapper.readListAll(categoryMap); // 추천순 정렬
		        } else if ("wishcnt".equals(sort)) {
		        	categoryMap.clear();
				 	categoryMap.put("start", String.valueOf(startRow));
				 	categoryMap.put("end", String.valueOf(endRow));
		            allprocuctList = mapper.wishListAll(categoryMap); // 최신순 정렬
		        } else {
		        	categoryMap.clear();
				 	categoryMap.put("start", String.valueOf(startRow));
				 	categoryMap.put("end", String.valueOf(endRow));
		            allprocuctList = mapper.allproductList(categoryMap); // 기본 정렬
		        }
		        showProduct(allprocuctList);
		    }
		model.addAttribute("allprocuctList",productList);
		model.addAttribute("allCnt",allCnt);
		model.addAttribute("pageNum",pageNum);
	    model.addAttribute("pageSize",pageSize);
	    model.addAttribute("sort",sort);
	    
	    int pageCount = allCnt / pageSize + ( allCnt % pageSize == 0 ? 0 : 1);
		 
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
	public void areaprodutlist(Model model,int areaNum, int pageNum,String area1,String sort) {//area1 상품리스트
		int cnt = mapper.productCnt(Integer.parseInt(area1));
		List<AllProductDTO> productlist = Collections.EMPTY_LIST ;
		if(cnt>0){
			productlist = mapper.productlist(area1);
			showProduct(productlist);
		}
		model.addAttribute("cnt",cnt);		
		model.addAttribute("productlist",productList);
	}
	
	
	public void showProduct(List<AllProductDTO> alprList) {
		productList.clear();
		ProductListDTO dto = null;
		for (AllProductDTO alprDTO : alprList) {
			seasonCategoryMap.put("username", alprDTO.getUsername());
			seasonCategoryMap.put("productnum", alprDTO.getProductnum());
			String keyword = alprDTO.getProductnum() + "_1%";
			seasonCategoryMap.put("keyword", keyword);
			dto = mainMapper.findProductListValue(seasonCategoryMap);
			productList.add(dto);		
		}
	}
	
	@Override
	public void arealist(int areaNum, Model model) {
		int pageSize = 8;
	    int startRow = (areaNum - 1) * pageSize + 1;
	    int endRow = areaNum * pageSize;
	    int count = mapper.allCnt();
	    List<AreaDTO> arealist = Collections.EMPTY_LIST;
	    if(count > 0 ) {
	    	categoryMap.put("start", String.valueOf(startRow));
	    	categoryMap.put("end", String.valueOf(endRow));
	    	arealist = mapper.arealistall(categoryMap); 			
	    }
	    model.addAttribute("arealist",arealist);
	    model.addAttribute("count",count);
	    model.addAttribute("areaNum",areaNum);
	    model.addAttribute("pageSize",pageSize);
	    
	    int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
		 
        int startPage = (int)(areaNum/10)*10+1;
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
