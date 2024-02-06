package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.ui.Model;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ProductCategoryDTO;

public interface AreaMapper {

	public List<AreaDTO> arealist();
	public int allCnt();
	public List<AllProductDTO> allproductList (HashMap<String, String> map);
	public List<AllProductDTO> readListAll (HashMap<String, String> map);
	public List<AllProductDTO> wishListAll (HashMap<String, String> map);
	
	public List<AreaDTO> arealistdetail (String area1);
	public List<AllProductDTO>productlist (String area1);
	public int productCnt(int area1);
	
	public int cntDetail(HashMap<String, String> map);
	public List<AllProductDTO> productlistdetail(HashMap<String, String> map);
	
	public List<AreaDTO>arealistall(HashMap<String, String> map);
	public List<AreaDTO>arealistdetailpage(HashMap<String, String> map);
	
}
 