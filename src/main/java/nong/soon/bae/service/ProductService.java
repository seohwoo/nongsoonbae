package nong.soon.bae.service;

import java.util.List;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ImagesDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;

public interface ProductService {
	// 내 이름 가져오기
	public String selectMyName(String username);
	
	// 내 상점 정보 등록하기 
	public void shopListInsert(ShopListDTO SLdto);
	
	// 내 상점 테이블 만들기
	public void createProduct(String username);
	
	// 카테고리 대분류
	public List<ProductCategoryDTO> selectCate1();
	
	// 카테고리 중분류
	public List<ProductCategoryDTO> selectCate2(int cate1);
	
	// 카테고리 소분류
	public List<ProductCategoryDTO> selectCate3(int cate1, int cate2);	
	
	// 상품 등록하기
	public void productInsert(AllProductDTO APdto);	
	
	// 상품 등록한 직후의 productnum 구하기	
	public List<AllProductDTO> selectAllProductLastProductNum(String username);	
	
	// 상품 등록할 때 상품 리뷰 테이블 만들기
	public void createReviews(String createReviewsProductnum);	
	
	// 상점 주소 가져오는 코드
	public String selectAddress(String username);	
	
	// area1 값 가져오는 코드
	public int selectArea1(String area1Address);	
	
	// area2 값 가져오는 코드
	public int selectArea2(String area2Address, int area1);
	
	// 상품 등록할 때 이미지 넣기
	public void imagesInsert(ImagesDTO Idto);
	
	// 상품 등록할 때 username_product에 옵션들 넣기
	public void optionInsert(ProductDTO Pdto);	
	
	// FINISH
	
	// TEST
	
	// // 내 상점 페이지에 필요한 정보들 가져오기
	public ShopListDTO selectMyShop(String username);
	
	// 유저의 상품들 가져오기
	public List<AllProductDTO> selectUsernameProduct(String username);	
	
	
	// sample
	public List<ProductDTO> sampleDetails(String username, String productnum);
}


	/* 가장 최근의 상품번호값 없으면 카운트0 있으면 상품 수
	//public int selectLastProductNumCnt(String keyword, String username);
	
	// 상품번호가 있으면 상품번호 뽑아오는거
	//public List<AllProductDTO> selectLastProductNum(String keyword);
	
	//public List<String> selectOptionNum(String keyword, String username);
	*/
