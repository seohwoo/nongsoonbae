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
import nong.soon.bae.repository.CategoryMapper;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryMapper mapper;
	
	@Autowired
	private HashMap<String, String> categoryMap;

	@Override
	public List<ProductCategoryDTO> cateMenu(Model model) { //대분류 카테고리 조회 
		return mapper.catelist();
	}

	@Override
	public void allproductlist(Model model) {
		int allCnt = mapper.allCnt();
		List<AllProductDTO> allprocuctList = Collections.EMPTY_LIST ;
		if(allCnt >0 ) {
			allprocuctList = mapper.allproductList();
		}
		model.addAttribute("allprocuctList",allprocuctList);
		model.addAttribute("allCnt",allCnt);
	}
		
	
	
	@Override
	public void catelistdeatil(Model model, String cate1) {
		List<ProductCategoryDTO> catelistdetail = mapper.catelistdetail(cate1);
		model.addAttribute("catelistdetail",catelistdetail);
		
	}

	@Override
	public void cateprodutlist(Model model, String cate1) {
		int cnt = mapper.productCnt(Integer.parseInt(cate1));
		List<AllProductDTO> productlist = Collections.EMPTY_LIST ;
		if(cnt>0){
			productlist = mapper.productlist(cate1);
		}
		
		model.addAttribute("cnt",cnt);
		System.out.println(cnt);
		model.addAttribute("productlist",productlist);
		System.out.println(productlist);
	}
	
	
	@Override
	public void cateprodictlistdetail(Model model, String cate1, String cate2) {
		categoryMap.put("cate1", cate1);
		categoryMap.put("cate2", cate2);
		int cntDetail = mapper.cntDetail(categoryMap);
	}
	
	@Override
	public void catelist(Model model, String cate1, String cate2, String cate3 ){ 
		categoryMap.put("cate1",cate1);
		categoryMap.put("cate2",cate2);
		categoryMap.put("cate3",cate3);
		int cnt = mapper.cateCnt(categoryMap);
		List<ProductCategoryDTO> menu = Collections.EMPTY_LIST ;
		//menu = mapper.catelist(cate1);
		model.addAttribute("menu",menu);
		model.addAttribute("cnt",cnt);
	}

	@Override
	public void cateDetail( Model model, String cate1, String cate2) {
		categoryMap.put("cate1",cate1);
		categoryMap.put("cate2",cate2);
		int cnt = mapper.cateCnt(categoryMap);
		List<AllProductDTO> list = Collections.EMPTY_LIST ;
		if(cnt >0) {
			list=mapper.cateDetail(cate1,cate2);
		}
		model.addAttribute("list",list);
		System.out.println(list);
		model.addAttribute("cnt",cnt);
		System.out.println(cnt);
	}

	@Override
	public List<AllProductDTO> cateproduct(){
		return null;
	}

	

	

	
}
