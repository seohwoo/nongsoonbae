 package nong.soon.bae.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ImagesDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;

public interface ProductMapper {	
	// 내 이름 가져오기
	public String selectMyName(String username);
	
	// 내 상점 정보 등록하기 
	public void shopListInsert(ShopListDTO SLdto);
	
	// 내 상점 테이블 만들기
	public void createProduct(String username);
	
	// FINISH
	
	// TEST
	
	
	// 상품 등록하기
	public void productInsert(AllProductDTO APdto);
	
	// 상점 주소 가져오는 코드
	public String selectAddress(String username);
	
	// area1 값 가져오는 코드
	public int selectArea1(String area1Address);
	
	// area2 값 가져오는 코드
	public int selectArea2(@Param("area1") int area1, 
						   @Param("area2Address") String area2Address);
	
	// 상품 등록할 때 이미지 넣기
	public void imagesInsert(ImagesDTO Idto);
	
	// 상품 등록할 때 username_product에 옵션들 넣기
	public void optionInsert(ProductDTO Pdto);
	
	// 카테고리 대분류
	public List<ProductCategoryDTO> selectCate1();
	public List<ProductCategoryDTO> selectCate2(int cate1);
	public List<ProductCategoryDTO> selectCate3(@Param("cate1") int cate1, 
												@Param("cate2") int cate2);
	
	public List<AllProductDTO> selectAllProductLastProductNum(String username);
	
	// 내 상점 페이지에 필요한 정보들 가져오기
	public ShopListDTO selectMyShop(String username);
	
	// 유저의 상품들 가져오기
	public List<AllProductDTO> selectUsernameProduct(String username);
	
	// 상품 등록할 때 상품 리뷰 테이블 만들기
	public void createReviews(String createReviewsProductnum);
	
	
	// sample
	public String sampleAddress(String username);
}


	/* 가장 최근의 상품번호값 없으면 카운트0 있으면 상품 수
	//public int selectLastProductNumCnt(@Param("keyword") String keyword, 
	//		  						   @Param("username") String username);
	
	// 상품번호가 있으면 상품번호 뽑아오는거
	//public List<AllProductDTO> selectLastProductNum(String keyword);
	
	//public List<String> selectOptionNum(@Param("keyword") String keyword, 
								  @Param("username") String username);
	*/
