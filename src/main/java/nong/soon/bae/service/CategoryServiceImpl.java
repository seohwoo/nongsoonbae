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
import nong.soon.bae.repository.CategoryMapper;
import nong.soon.bae.repository.MainMapper;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryMapper mapper;
	
	@Autowired
	private HashMap<String, String> categoryMap;
	@Autowired
	private HashMap<String, String> seasonCategoryMap;
	@Autowired
	private MainMapper mainMapper;
	@Autowired
	private ArrayList<ProductListDTO> productList;

	//대분류 카테고리 분류
	@Override
	public List<ProductCategoryDTO> cateMenu(Model model) { //대분류 카테고리 조회 
		return mapper.catelist();
	}
	
	// 전체상품리스트
	@Override
	public void allproductlist(Model model,String sort, int pageNum) { //전체 품목 뽑기
		int pageSize = 10;
	    int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
		int allCnt = mapper.allCnt();
		List<AllProductDTO> allprocuctList = Collections.EMPTY_LIST ;
		if(allCnt >0 ) {
			categoryMap.clear();
		 	categoryMap.put("start", String.valueOf(startRow));
		 	categoryMap.put("end", String.valueOf(endRow));
			 if ("readcnt".equals(sort)) {		
				 	allprocuctList = mapper.readListAll(categoryMap); // 인기순(조회수) 정렬
		        } else if ("wishcnt".equals(sort)) {
		            allprocuctList = mapper.wishListAll(categoryMap); // 찜 정렬
		        }else if ("cheap".equals(sort)) {
		            allprocuctList = mapper.cheapListAll(categoryMap); // 가격낮은순 정렬
		        } else {
		            allprocuctList = mapper.allproductList(categoryMap); // 기본(최신순) 정렬
		        }
			 	ArrayList<ProductListDTO> localProductList = showProduct(allprocuctList);
		        model.addAttribute("allproductList", localProductList);
		    }
		
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
	
		
	//중분류 카테고리 목록
	@Override
	public void catelistdeatil(Model model, String cate1) {
		List<ProductCategoryDTO> catelistdetail = mapper.catelistdetail(cate1);
		model.addAttribute("catelistdetail",catelistdetail);
		
	}
	//대분류 선택에 따른 상품 목록 ex: cate1 = 1 과일 상품 목록
	@Override
	public void cateprodutlist(Model model, String cate1,int pageNum, String sort) {
		
		int pageSize = 10;
	    int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
		int cnt = mapper.productCnt(Integer.parseInt(cate1));
		List<AllProductDTO> productlist = Collections.EMPTY_LIST ;
		if(cnt>0){
			categoryMap.clear();
		 	categoryMap.put("start", String.valueOf(startRow));
		 	categoryMap.put("end", String.valueOf(endRow));
		 	categoryMap.put("cate1", cate1);
			 if ("readcnt".equals(sort)) {		
				  	productlist = mapper.readList(categoryMap); // 인기순 정렬
		        } else if ("wishcnt".equals(sort)) {
				 	productlist = mapper.wishList(categoryMap); // 찜 정렬
		        }else if ("cheap".equals(sort)) {
				 	productlist = mapper.cheapList(categoryMap); // 가격낮은순 정렬
		        } else { 
				 	productlist = mapper.cateprodutList(categoryMap); // 기본(최신순) 정렬
		        }
			 	ArrayList<ProductListDTO> localProductList = showProduct(productlist);
		        model.addAttribute("productlist", localProductList); 
		    }
		
		model.addAttribute("cnt",cnt);
		model.addAttribute("pageNum",pageNum);
	    model.addAttribute("pageSize",pageSize);
	    model.addAttribute("sort",sort);
	    model.addAttribute("cate1",Integer.parseInt(cate1));
		
	    
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
	
	@Override //cate1 광고상품
	public void adcateprodutlist(Model model, String cate1) {
		int adCnt = mapper.adProductCnt(Integer.parseInt(cate1));
		List<AllProductDTO> adproductlist = Collections.EMPTY_LIST ;
		if (adCnt > 0) {
			categoryMap.clear();
			categoryMap.put("cate1", cate1);
			adproductlist = mapper.adcateprodutList(categoryMap);
			ArrayList<ProductListDTO> localAdProductList = adshowProduct(adproductlist);
            model.addAttribute("adproductlist", localAdProductList);
		}
		model.addAttribute("adCnt",adCnt);
		
	}
	
	
	//중분류 선택에 따른 상품 목록 ex: cate1 = 1 , cate2 = 1 감/딸기/토마토 상품 목록
	@Override
	public void cateproductlistdetail(Model model, String cate1, String cate2
									,int pageNum,String sort) {
	
		int pageSize = 10;
	    int startRow = (pageNum - 1) * pageSize + 1;
	    int endRow = pageNum * pageSize;
		categoryMap.put("cate1", cate1);
		categoryMap.put("cate2", cate2);
		int cntDetail = mapper.cntDetail(categoryMap);
		List<AllProductDTO> productlistdetail = Collections.EMPTY_LIST ; 
		if(cntDetail>0){
			categoryMap.clear();
		 	categoryMap.put("start", String.valueOf(startRow));
		 	categoryMap.put("end", String.valueOf(endRow));
		 	categoryMap.put("cate1", cate1);
		 	categoryMap.put("cate2", cate2);
			 if ("readcnt".equals(sort)) {		
				 	productlistdetail = mapper.readListDetail(categoryMap); // 인기순 정렬
		        } else if ("wishcnt".equals(sort)) {
				 	productlistdetail = mapper.wishListDetail(categoryMap); // 찜 정렬
		        }else if ("cheap".equals(sort)) {
				 	productlistdetail = mapper.cheapListDetail(categoryMap); // 찜 정렬
		        } else {
				 	productlistdetail = mapper.productlistdetail(categoryMap); // 기본(최신순) 정렬
		        }
			 	ArrayList<ProductListDTO> localProductList = showProduct(productlistdetail);
		        model.addAttribute("productlistdetail", localProductList); 
		    }
		model.addAttribute("cntDetail",cntDetail);
		model.addAttribute("pageNum",pageNum);
	    model.addAttribute("pageSize",pageSize);
	    model.addAttribute("sort",sort);
	    model.addAttribute("cate1",Integer.parseInt(cate1));
	    model.addAttribute("cate2",Integer.parseInt(cate2));
		
	    
	    int pageCount = cntDetail / pageSize + ( cntDetail % pageSize == 0 ? 0 : 1); 
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
	public void adcateproductdetail(Model model, String cate1, String cate2) {
		categoryMap.put("cate1", cate1);
		categoryMap.put("cate2", cate2);
		int adCntDetail = mapper.adCntDetail(categoryMap); 
		List<AllProductDTO> adproductdetail = Collections.EMPTY_LIST ;
		if(adCntDetail > 0) {
			adproductdetail = mapper.adproductdetail(categoryMap);
			ArrayList<ProductListDTO> localAdProductList = adshowProduct(adproductdetail);
            model.addAttribute("adproductdetail", localAdProductList);
		}
		model.addAttribute("adCntDetail",adCntDetail);
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
	

	
		
}

	
	

