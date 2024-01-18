package nong.soon.bae.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;
import nong.soon.bae.bean.UsersDTO;

public interface ProductService {
	// 내 상점 정보 등록하기 
	public void allShopList(ShopListDTO dto);
	
	// 개인 상점(테이블) 만들기
	public void createProduct(String username);

	// 개인 이미지(테이블) 만들기
	public void createImages(String username);
	
	// 상품 등록하기
	public int productInsert(ProductDTO product, List<MultipartFile> files, String path);
	
	// 이미지 파일 넣기
	public int imagesInsert(List<MultipartFile> files, String path, String username);
	
	// 상품 옵션 추가
	public void optionInsert(ProductDTO product);
	
	// 가장 최근에 상품등록한 productnum값 가져오기
	public String selectProductnum(String username);
	
	// 전체 상품 테이블에 상품 등록할 때 넣을 유저의 주소 찾기
	public List<AreaDTO> selectAddress(String username);
	
	// 전체 상품 테이블에 상품 등록하기
	public void allProductInsert(AllProductDTO dto);
	
	// 상품 등록할 때 상품 번호로 리뷰테이블 만들기
	public void createReviews(String productnum);
	
	// FINISH
		
	// 상품 상세정보 보기
	public ProductDTO productDetail(String productnum, String username, String otherUsername);		
	
	// 상품넘버로 유저네임찾기
	public String selectUsername(String productnum);
	
	public AreaDTO selectArea(String productnum, String username, String otherUsername);
	
	public String selectAreaName1(AreaDTO areaDTO);
	
	public String selectAreaName2(AreaDTO areaDTO);
	
	public String selectName(String username);
	
	public List<ProductDTO> selectOption(String username, String optionstatus, String otherUsername);
	
	public List<AllProductDTO> allProduct();

	// 상품 찜하기 유무
	public int selectProductPickCount(String username, String productnum);	
		
	// 상품 찜하기
	public void productPick(String username, String productnum, String otherUsername);

	// 찜하기 누를 때마다 상품 찜 1씩 증가
	public void updateProductWishcount(String otherUsername, String productnum);	

	// 상품 찜 삭제하기
	public void productPickDelete(String username, String productnum);
	
	// 찜 삭제하기 누를 때마다 상품 찜 1씩 감소
	public void deleteProductWishcount(String otherUsername, String productnum);	
	
	// 장바구니 상품 유무
	public int selectProductShoppingCartCount(String username, String productnum);
	
	// 장바구니 상품 담기
	public void productShoppingCart(String username, String productnum, String otherUsername);	
	
	// 장바구니 상품 삭제하기
	public void productShoppingCartDelete(String username, String productnum);
	
	// 상품 클릭시 조회수 증가
	public void updateReadcount(String username, String productnum);

	// 상품 조회한 유저
	public void productReadcountInsert(String username, String productnum);	
	
	// 오늘 상품 조회한 사람 찾기
	public int todayProductReadcount(String username, String productnum, String todaydate);
	
	
	
	// TEST
	
	public List<ProductDTO> myProduct(String username);
	

	
	
	
	
	public ProductDTO productInfo(ProductDTO productDTO);
	
	
}
