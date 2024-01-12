package nong.soon.bae.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.UsersDTO;

public interface ProductService {
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
	
	// 전체 상품 테이블에 상품 등록하기
	public void allproduct(AllProductDTO dto);
	
	// 상품 등록할 때 상품 번호로 리뷰테이블 만들기
	public void createReviews(String productnum);
	
	// FINISH
	
	
	
	// TEST
	
	public List<ProductDTO> myProduct(String username);
	

	
	
	
	
	public ProductDTO productInfo(ProductDTO productDTO);
	
	
}
