package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;



import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;


public interface AreaMapper {

	public List<AreaDTO>arealistall(HashMap<String, String> map); //지역분류

	//전체상품정렬
	public List<AllProductDTO> allproductList (HashMap<String, String> map);
	public List<AllProductDTO> adAllProduct ();
	public List<AllProductDTO> readListAll (HashMap<String, String> map);
	public List<AllProductDTO> wishListAll (HashMap<String, String> map);
	public List<AllProductDTO> cheapListAll (HashMap<String, String> map);
	public int allCnt();
	public int adAllCnt();
	
	//area1의 값을 받아 넘어온 상품 목록 ex: 서울(area1=1)에 대한 상품리스트
	public List<AllProductDTO> areaprodutList (HashMap<String, String> map);
	public List<AllProductDTO> adareaprodutList (HashMap<String, String> map);
	public List<AllProductDTO> readList (HashMap<String, String> map);
	public List<AllProductDTO> wishList (HashMap<String, String> map);
	public List<AllProductDTO> cheapList (HashMap<String, String> map);
	public int productCnt(int area1);
	public int adProductCnt(int area1);
	
	
	
}
 