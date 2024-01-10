package nong.soon.bae.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.UsersDTO;

public interface ProductMapper {
	// 개인 상점(테이블) 만들기
	public void createProduct(String username);

	// 개인 이미지(테이블) 만들기
	public void createImages(String username);
	
	// FINISH
	
	// 판매 정보 넣기
	public int productInsert(ProductDTO product);
	
	// TEST
	
	public List<ProductDTO> myProduct(String username);
	
	
	public void allproduct(AllProductDTO dto);
	
	// 가장 최근에 상품등록한 productnum값 가져오기
	public String selectProductnum(String username);
	
	// TEST0110
	public int imagesInsert(@Param("username") String username,
							@Param("filename") String filename);
	
}
