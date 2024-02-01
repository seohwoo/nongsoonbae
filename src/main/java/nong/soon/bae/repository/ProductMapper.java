 package nong.soon.bae.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ImagesDTO;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ReviewsDTO;
import nong.soon.bae.bean.ShopListDTO;

public interface ProductMapper {	
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
	public List<ProductCategoryDTO> selectCate3(@Param("cate1") int cate1, 
												@Param("cate2") int cate2);	
		
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
	public int selectArea2(@Param("area1") int area1, 
						   @Param("area2Address") String area2Address);
	
	// 상품 등록할 때 이미지 넣기
	public void imagesInsert(ImagesDTO Idto);
	
	// 상품 등록할 때 username_product에 옵션들 넣기
	public void optionInsert(ProductDTO Pdto);
	
	//상점개설여부(유미)
	String CheckMyShop(String username);
	
	// FINISH
	
	// TEST

	// 상품 정보 페이지
	public AllProductDTO selectProductInfo(@Param("follow") String follow, 
										   @Param("productnum") String productnum);
	
	// 상품 올린 사람의 주소, 이름, 팔로우 찾기
	public AllProductDTO selectProductNameAddressFollowers(String follow);
	
	// 상품 옵션들 가져오기
	public List<ProductDTO> selectProductOptionAll(@Param("follow") String follow, 
			   									   @Param("productnum") String productnum);
	
	// 상품 사진 가져오기
	public List<AllProductDTO> selectProductImagesAll(@Param("follow") String follow, 
													  @Param("productnum") String productnum);

	// 상품 찜하기
	public void InsertProductPick(MyPageDTO MPdto);
	
	// 상품에 찜 +1 하기
	public void allproductWishcntPlus(String productnum);
	
	// 상품 찜 유무
	public int selectPickCount(@Param("username") String username, 
							   @Param("productnum") String productnum);

	// 마이페이지에 상품 찜 삭제하기
	public void deleteProductPick(@Param("username") String username, 
								  @Param("productnum") String productnum);
	
	// Allproduct 상품에 찜 -1 하기
	public void allproductWishcntMinus(String productnum);
	
	// 농부 팔로우하기
	public void InsertUsernameFollow(MyPageDTO MPdto);	
	
	// 농부 팔로우하면 userdetails에 followers +1 하기
	public void userdetailsUpdateFollowersPlus(String follow);
	
	// 마이페이지 농부 구독 유무
	public int selectFollowCount(@Param("username") String username,
								 @Param("follow") String follow);
	
	// 농부 팔로우 취소하기
	public void deleteFollow(@Param("username") String username,
						 	 @Param("follow") String follow);
	
	// 농부 팔로우 취소하면 userdetails에 followers -1 하기
	public void userdetailsUpdateFollowersMinus(String follow);
	
	
	
	
	// TEST
	
	
	
	
	// 내 상점 페이지에 필요한 정보들 가져오기
	public ShopListDTO selectMyShop(String username);
	
	// 유저의 상품들 가져오기
	public List<AllProductDTO> selectUsernameProduct(String username);
	
	// 이름이랑 상품 가져오는 코드
	public AllProductDTO selectAllProductPlusNameFollowers(String productnum);
	
	// 상품 등록한 상점 area1 주소
	public AreaDTO selectArea1Address(int area1);
	
	// 상품 등록한 상점 area2 주소
	public AreaDTO selectArea2Address(@Param("area1") int area1, 
									  @Param("area2") int area2);	
	
	// 상품 옵션 가져오기
	public List<ProductDTO> selectProductOption(@Param("follow") String follow, 
												@Param("productnum") String productnum);
	
	// 상품 이미지 가져오기 
	public List<ImagesDTO> selectProductImages(@Param("follow") String follow, 
											   @Param("productnum") String productnum);

	// 상품 리뷰쓰기
	public void reviewInsert(ReviewsDTO Rdto);
	
	
	// 상품 전체목록 보기
	public List<AllProductDTO> selectAllproduct();
}
	
	

	/* 가장 최근의 상품번호값 없으면 카운트0 있으면 상품 수
	//public int selectLastProductNumCnt(@Param("keyword") String keyword, 
	//		  						   @Param("username") String username);
	
	// 상품번호가 있으면 상품번호 뽑아오는거
	//public List<AllProductDTO> selectLastProductNum(String keyword);
	
	//public List<String> selectOptionNum(@Param("keyword") String keyword, 
								  @Param("username") String username);
	*/
