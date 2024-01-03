package nong.soon.bae.service;

import java.util.List;

import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.UsersDTO;

public interface ProductService {
	// 카테고리 정보 가져오기
	public List<ProductCategoryDTO> selectProductcategory();
	
	// 개인 상점(테이블) 만들기
	public void createProduct(String username);
	
	// 개인 상점 sequence 만들기
	public void createSEQ(String username);
	
	// FINISH
	
	// 판매 정보 넣기
	public void productInsert(ProductDTO product, String username);
	
	// TEST
	public List<ProductCategoryDTO> selectCate1();
	
	public List<ProductCategoryDTO> selectCate2();
	
	public List<ProductCategoryDTO> selectCate3();
}
