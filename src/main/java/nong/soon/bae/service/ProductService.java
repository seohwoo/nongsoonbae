package nong.soon.bae.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ImagesDTO;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ReviewsDTO;
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
	
	public ProductCategoryDTO selectCate4(int cate1,int cate2,int cate3);
	
	//상품정보를 가져옴
	public AllProductDTO findProductInfoToReadcnt(String productnum);
	
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

	// 상품 찜하기
	public void InsertProductPick(MyPageDTO MPdto);
	
	// 상품에 찜 +1 하기
	public void allproductWishcntPlus(String productnum);
	
	// 상품 찜 유무
	public int selectPickCount(String username, String productnum);
	
	// 마이페이지에 상품 찜 삭제하기
	public void deleteProductPick(String username, String productnum);
	
	// Allproduct 상품에 찜 -1 하기
	public void allproductWishcntMinus(String productnum);	
	
	// 농부 팔로우하기
	public void InsertUsernameFollow(MyPageDTO MPdto);
	
	// 농부 팔로우하면 userdetails에 followers +1 하기
	public void userdetailsUpdateFollowersPlus(String follow);	
	
	// 마이페이지 농부 구독 유무
	public int selectFollowCount(String username, String follow);
	
	// 농부 팔로우 취소하기
	public void deleteFollow(String username, String follow);
	
	// 농부 팔로우 취소하면 userdetails에 followers -1 하기
	public void userdetailsUpdateFollowersMinus(String follow);		
	
	
	
	
	
	
	
	
	
	
	String CheckMyShop(String username);
	
	// FINISH
	
	// TEST
	
	// 상품 정보 페이지
	public AllProductDTO selectProductInfo(String follow, String productnum);
	
	// 상품 올린 사람의 주소, 이름, 팔로우 찾기
	public AllProductDTO selectProductNameAddressFollowers(String follow, String productnum);

	// 상품 옵션들 가져오기
	public List<ProductDTO> selectProductOptionAll(String follow, String productnum);
	
	// 상품 사진 가져오기
	public List<AllProductDTO> selectProductImagesAll(String follow, String productnum);

	
	
	
	
	
	
	// TEST
	
	// 장바구니 담기
	public void insertShopping(MyPageDTO MPdto);	
	
	
	
	// 내 상점 페이지에 필요한 정보들 가져오기
	public ShopListDTO selectMyShop(String username);
	
	// 유저의 상품들 가져오기
	public List<AllProductDTO> selectUsernameProduct(String username);	
	
	
	// 이름이랑 상품 가져오는 코드
	public AllProductDTO selectAllProductPlusNameFollowers(String productnum);
	
	// 상품 등록한 상점 area1 주소
	public AreaDTO selectArea1Address(int area1);
	
	// 상품 등록한 상점 area2 주소
	public AreaDTO selectArea2Address(int area1, int area2);
	
	// 상품 옵션 가져오기
	public List<ProductDTO> selectProductOption(String follow, String productnum);

	// 상품 이미지 가져오기 	
	public List<ImagesDTO> selectProductImages(String follow, String productnum); 

	// 상품 리뷰쓰기
	public int reviewInsert(List<MultipartFile> files, ReviewsDTO Rdto, String path);
	
	// 리뷰 등록할 때 이미지 넣기
	public int ReviewsimagesInsert(List<MultipartFile> files, String path, String username, String productnum);
	
	// 상품 리뷰 가져오기
	public List<ReviewsDTO> selectReviewsAll(String follow, String productnum);
	
	// 상품 리뷰 수
	public int selectReviewsCount(String productnum);
	
	// 상품 전체목록 보기
	public List<AllProductDTO> selectAllproduct();
	
	// shoplist에서 내 상점 정보 삭제하기
	public void deleteShoplist(String username);
	
	// productnum 값으로 리뷰 테이블 삭제하기
	public void dropReviewsTable(String productnum);
	
	// username으로 productnum 가져오기
	public List<String> selectUsernameProductnum(String username);	
	
	public void allproductUpdateContent(AllProductDTO dto);


	public void deleteProductOption(String username, String productnum);
	
	public void deleteAllproduct(String username, String productnum);
	
	// 상품 판매시 재고 업데이트
	public List<MyPageDTO> selectMypage3(String username);
	
	public void updateProductCount(String follow, int cnt, String optionnum);
	
	// 상품 조회수 증가
	public void updateReadcntPlus(String productnum);
	
	// 상품 조회한 유저정보 넣기
	public void productReadcntInsert(String username, String productnum);
	
	// 오늘 상품 조회한 유저 찾기
	public int selectTodayReadcntUsername(String username, String productnum, String todaydate);

	// 상점 폐쇄할 때 allproduct 상품 삭제
	public void allproductDeleteMyUsername(String username);
	
	// 상점 폐쇄할 때 username_Product 드랍
	public void dropUsernameProduct(String username);
	
	// 상품 리뷰 쓴 사람 가져오기
	public List<ReviewsDTO> selectReviewsUsername(String productnum);
	
	// 리뷰 쓴 사람들 가져오기 최종
	public List<ReviewsDTO> ReviewsInfoFinal(String productnum, String follow, String usernames, String formatdate);

	// 리뷰 삭제하기
	public void myReviewsDelete(String productnum, String myName);
	
	/** 본인 상점정보 확인하기 */
	public void findMyShopInfo(Model model, String username);
	
	/** 본인 판매내역 차트 확인하기 */
	public void findMySellChart(Model model, String username);
	
	public void deleteReviewImages(String productnum, String myName, String path);
	
}


	/* 가장 최근의 상품번호값 없으면 카운트0 있으면 상품 수
	//public int selectLastProductNumCnt(String keyword, String username);
	
	// 상품번호가 있으면 상품번호 뽑아오는거
	//public List<AllProductDTO> selectLastProductNum(String keyword);
	
	//public List<String> selectOptionNum(String keyword, String username);
	*/
