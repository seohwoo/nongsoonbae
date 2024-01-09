package nong.soon.bae.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.repository.CategoryMapper;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryMapper mapper;
	
	@Autowired
	private HashMap<String, String> categoryMap;

	@Override
	public List<ProductCategoryDTO> catemenu(Model model) { //��з� ī�װ� ��ȸ 
		return mapper.catelist();
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
