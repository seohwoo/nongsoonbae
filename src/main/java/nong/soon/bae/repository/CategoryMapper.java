package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ProductCategoryDTO;

public interface CategoryMapper {
 
	public List<ProductCategoryDTO> catelist(); 
	public int allCnt();
	public List<AllProductDTO> allproductList ();
	public List<ProductCategoryDTO> catelistdetail (String cate1);
	public List<AllProductDTO>productlist (String cate1);
	public int productCnt(int cate1);
	public int cntDetail(HashMap<String, String> map);
	
	
	public List<AllProductDTO> cateDetail(String cate1,String cate2);	
	public int cateCnt(HashMap<String, String> map);
	
}
