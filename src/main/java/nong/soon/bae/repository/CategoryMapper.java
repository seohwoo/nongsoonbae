package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ProductCategoryDTO;

public interface CategoryMapper {
	public List<ProductCategoryDTO> catemenu(); // 대분류 
	public List<ProductCategoryDTO> catelist(int cate1); //중분류
	
	public List<AllProductDTO> cateDetail(String cate1,String cate2);
	public int cateCnt(HashMap<String, String> map);
	
}
