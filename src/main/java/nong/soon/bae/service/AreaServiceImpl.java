package nong.soon.bae.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.repository.AreaMapper;

@Service
public class AreaServiceImpl implements AreaService{
	@Autowired
	private AreaMapper mapper;
	@Autowired
	private HashMap<String, String> categoryMap;

	
	@Override
	public List<AreaDTO> areaMenu(Model model) {
	
		return mapper.arealist();
	}	
	@Override
	public void allproductlist(Model model) { //전체항목
		int allCnt = mapper.allCnt();
		List<AllProductDTO> allprocuctList = Collections.EMPTY_LIST ;
		if(allCnt >0 ) {
			allprocuctList = mapper.allproductList();
		}
		model.addAttribute("allprocuctList",allprocuctList);
		model.addAttribute("allCnt",allCnt);
	}
	
	@Override
	public void arealistdeatil(Model model,String area1) { //area1 리스트
		List<AreaDTO> arealistdetail = mapper.arealistdetail(area1);
		model.addAttribute("arealistdetail",arealistdetail);
			}
	
	
	@Override
	public void areaprodutlist(Model model, String area1) {//area1 상품리스트
		int cnt = mapper.productCnt(Integer.parseInt(area1));
		List<AllProductDTO> productlist = Collections.EMPTY_LIST ;
		if(cnt>0){
			productlist = mapper.productlist(area1);
		}
		
		model.addAttribute("cnt",cnt);
		System.out.println(cnt);
		model.addAttribute("productlist",productlist);
		System.out.println(productlist);
	}
	
	@Override
	public void areaprodictlistdetail(Model model, String area1, String area2) { //area2 상품리스트
		categoryMap.put("area1", area1);
		categoryMap.put("area2", area2);
		int cntDetail = mapper.cntDetail(categoryMap);
		List<AllProductDTO> productlistdetail = Collections.EMPTY_LIST ; 
		if (cntDetail >0 ) {
			productlistdetail = mapper.productlistdetail(categoryMap);
		}
		model.addAttribute("cntDetail", cntDetail);
		model.addAttribute("productlistdetail",productlistdetail);
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
	/*@Override
	public void arealistpage(int areaNum, Model model, String area1) {
		int listpageSize = 8;
	    int startRow = (areaNum - 1) * listpageSize + 1;
	    int endRow = areaNum * listpageSize;
		int areacnt = mapper.productCnt(Integer.parseInt(area1));
		List<AreaDTO> arealistdetail = Collections.EMPTY_LIST;
		if(areacnt > 0) {
			categoryMap.put("area", area1);
			categoryMap.put("start", String.valueOf(startRow));
	    	categoryMap.put("end", String.valueOf(endRow));
	    	arealistdetail = mapper.arealistdetailpage(categoryMap);
	    	
		}
		 	model.addAttribute("arealistdetail",arealistdetail);
		    model.addAttribute("areacnt",areacnt);
		    model.addAttribute("areaNum",areaNum);
		    model.addAttribute("listpageSize",listpageSize);
		
		    int pageCount = areacnt / listpageSize + ( areacnt % listpageSize == 0 ? 0 : 1);
			 
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
		
	}*/
}
