package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.ui.Model;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ProductCategoryDTO;

public interface CategoryMapper {
 
	public List<ProductCategoryDTO> catelist(); 
	public int allCnt();
	
	public List<AllProductDTO> allproductList (HashMap<String, String> map);
	public List<AllProductDTO> readListAll (HashMap<String, String> map);
	public List<AllProductDTO> wishListAll (HashMap<String, String> map);
	public List<AllProductDTO> cheapListAll (HashMap<String, String> map);
	
	public int productCnt(int cate1);
	public List<AllProductDTO> cateprodutList (HashMap<String, String> map);
	public List<AllProductDTO> readList (HashMap<String, String> map);
	public List<AllProductDTO> wishList (HashMap<String, String> map);
	public List<AllProductDTO> cheapList (HashMap<String, String> map);
	
	
	
	public List<ProductCategoryDTO> catelistdetail (String cate1);

	public int cntDetail(HashMap<String, String> map);
	public List<AllProductDTO> productlistdetail(HashMap<String, String> map);
	public List<AllProductDTO> readListDetail (HashMap<String, String> map);
	public List<AllProductDTO> wishListDetail (HashMap<String, String> map);
	public List<AllProductDTO> cheapListDetail (HashMap<String, String> map);
	
}
