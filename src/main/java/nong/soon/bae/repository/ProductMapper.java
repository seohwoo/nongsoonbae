package nong.soon.bae.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;
import nong.soon.bae.bean.UsersDTO;

public interface ProductMapper {	
	// 내 상점 정보 등록하기 
	public void allShopList(ShopListDTO dto);
	
	// 개인 상점(테이블) 만들기
	public void createProduct(String username);

	// 개인 이미지(테이블) 만들기
	public void createImages(String username);
	
	// 상품 등록하기
	public int productInsert(ProductDTO product);	
	
	// 이미지 파일 넣기
	public int imagesInsert(@Param("productnum") String productnum,
							@Param("filename") String filename,
							@Param("username") String username);
	
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
	
	public List<AllProductDTO> allProduct();
	
	// 상품 상세정보 보기
	public ProductDTO productDetail(@Param("productnum") String productnum,
								    @Param("username") String username);
	
	public AreaDTO selectArea(@Param("productnum") String productnum,
							  @Param("username") String username);
	
	public String selectAreaName1(AreaDTO areaDTO);
	
	public String selectAreaName2(AreaDTO areaDTO);
	
	public String selectName(String username);
	
	public List<ProductDTO> selectOption(
										 @Param("username") String username,
										 @Param("optionstatus") String optionstatus);
	// 찜하기
	public void productPick(@Param("username") String username, 
							@Param("productnum") String productnum);

	// 장바구니
	public void productShoppingCart(@Param("username") String username, 
									@Param("productnum") String productnum);
	
	// 찜하기 누를 때마다 1씩 증가
	public void updateProductWishcount(@Param("username") String username, 
									   @Param("productnum") String productnum);
	
	// 찜하기 유무
	public int selectProductPickCount(@Param("username") String username, 
			   						  @Param("productnum") String productnum);
	
	
	// TEST
	
	public List<ProductDTO> myProduct(String username);
	
	
	public ProductDTO productInfo(ProductDTO productDTO);
	
}
