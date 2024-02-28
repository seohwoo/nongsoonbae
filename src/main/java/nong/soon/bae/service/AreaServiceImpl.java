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
	

	
	//전체항목리스트
	@Override
	public void allproductlist(Model model, String sort, int pageNum) { //전체항목
	    int pageSize = 12;
	    int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
	    int allCnt = mapper.allCnt();
	    List<AllProductDTO> allproductList = Collections.EMPTY_LIST; // 변수명 수정
	    if (allCnt > 0) {
	        categoryMap.clear();
	        categoryMap.put("start", String.valueOf(startRow));
	        categoryMap.put("end", String.valueOf(endRow));
	        if ("readcnt".equals(sort)) {
	            allproductList = mapper.readListAll(categoryMap); // 인기순(조회수) 정렬
	        } else if ("wishcnt".equals(sort)) {
	            allproductList = mapper.wishListAll(categoryMap); // 찜 많은 순 정렬
	        } else if ("cheap".equals(sort)) {
	            allproductList = mapper.cheapListAll(categoryMap); // 가격 낮은 순 정렬
	        } else {
	            allproductList = mapper.allproductList(categoryMap); // 기본(최신)정렬
	        }
	        ArrayList<ProductListDTO> localProductList = showProduct(allproductList);
	        model.addAttribute("allproductList", localProductList); 
	    }
	    
	    // 페이징 처리 관련 모델 속성 추가
	    model.addAttribute("allCnt", allCnt);
	    model.addAttribute("pageNum", pageNum);
	    model.addAttribute("pageSize", pageSize);
	    model.addAttribute("sort", sort);
	    
	    int pageCount = allCnt / pageSize + (allCnt % pageSize == 0 ? 0 : 1);
	    int startPage = (int) ((pageNum - 1) / 10) * 10 + 1;
	    int pageBlock = 10;
	    int endPage = startPage + pageBlock - 1;
	    if (endPage > pageCount) {
	        endPage = pageCount;
	    }
	    
	    model.addAttribute("pageCount", pageCount);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("pageBlock", pageBlock);
	    model.addAttribute("endPage", endPage);
	}

	
	
	@Override
	public void adallproductlist(Model model) {
		int adAllCnt = mapper.adAllCnt();
		List<AllProductDTO> adAllprocuct = Collections.EMPTY_LIST ;
		if(adAllCnt > 0) {
			adAllprocuct = mapper.adAllProduct();	
			ArrayList<ProductListDTO> localAdProductList = adshowProduct(adAllprocuct);
            model.addAttribute("adAllprocuct", localAdProductList);
			
		}
		model.addAttribute("adAllCnt",adAllCnt);
	}
	
	//area1의 값을 받아 넘어온 상품 목록 ex: 서울(area1=1)에 대한 상품리스트
	@Override
	public void areaprodutlist(Model model,int areaNum, int pageNum,String area1,String sort) {//area1 상품리스트
		int pageSize = 12;
	    int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
	    int cnt = mapper.productCnt(Integer.parseInt(area1));
		List<AllProductDTO> productlist = Collections.EMPTY_LIST ;	
		if(cnt >0 ) {
			categoryMap.clear();
	        categoryMap.put("start", String.valueOf(startRow));
	        categoryMap.put("end", String.valueOf(endRow));
	        categoryMap.put("area1", area1);
			 if ("readcnt".equals(sort)) {		
				  	productlist = mapper.readList(categoryMap); // 인기순(조회수) 정렬
		        } else if ("wishcnt".equals(sort)) {
				 	productlist = mapper.wishList(categoryMap); // 찜많은순 정렬
		        }else if ("cheap".equals(sort)) {
				 	productlist = mapper.cheapList(categoryMap); // 가격 낮은순 정렬
		        } else {
				 	productlist = mapper.areaprodutList(categoryMap); // 기본(최신순) 정렬
		        }
			 	ArrayList<ProductListDTO> localProductList = showProduct(productlist);
		        model.addAttribute("productlist", localProductList); 
		    }
		
		model.addAttribute("cnt",cnt);
		model.addAttribute("pageNum",pageNum);
	    model.addAttribute("pageSize",pageSize);
	    model.addAttribute("sort",sort);
	    model.addAttribute("area1",Integer.parseInt(area1));
		
	    
	    int pageCount = cnt / pageSize + ( cnt % pageSize == 0 ? 0 : 1);
		 
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
	public void adareaprodutlist(Model model, String area1) {
		int adCnt = mapper.adProductCnt(Integer.parseInt(area1));
		List<AllProductDTO> adproductlist = Collections.EMPTY_LIST ;
		if (adCnt > 0) {
			categoryMap.clear();
			categoryMap.put("area1", area1);
			adproductlist = mapper.adareaprodutList(categoryMap);
			ArrayList<ProductListDTO> localAdProductList = adshowProduct(adproductlist);
            model.addAttribute("adproductlist", localAdProductList);
		}
		
		model.addAttribute("adCnt",adCnt);
		
	}
	
	
	public void exshowProduct(List<AllProductDTO> alprList) {
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
	
	
	public ArrayList<ProductListDTO> showProduct(List<AllProductDTO> alprList) {
	    ArrayList<ProductListDTO> localProductList = new ArrayList<>();
	    for (AllProductDTO alprDTO : alprList) {
	    	seasonCategoryMap.put("username", alprDTO.getUsername());
			seasonCategoryMap.put("productnum", alprDTO.getProductnum());
			String keyword = alprDTO.getProductnum() + "_1%";
			seasonCategoryMap.put("keyword", keyword);
	        ProductListDTO dto = mainMapper.findProductListValue(seasonCategoryMap);
	        localProductList.add(dto);
	    }
	    return localProductList;
	}

	public ArrayList<ProductListDTO> adshowProduct(List<AllProductDTO> alprList) {
	    ArrayList<ProductListDTO> localAdProductList = new ArrayList<>();
	    for (AllProductDTO alprDTO : alprList) {
	    	seasonCategoryMap.put("username", alprDTO.getUsername());
			seasonCategoryMap.put("productnum", alprDTO.getProductnum());
			String keyword = alprDTO.getProductnum() + "_1%";
			seasonCategoryMap.put("keyword", keyword);
	        ProductListDTO ad = mainMapper.findProductListValue(seasonCategoryMap);
	        localAdProductList.add(ad);
	    }
	    return localAdProductList;
	}
	
	//지역 분류 (서울,경기,인천 etc...)
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
