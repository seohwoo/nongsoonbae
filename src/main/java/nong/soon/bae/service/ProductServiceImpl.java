package nong.soon.bae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ImagesDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;
import nong.soon.bae.repository.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper mapper;

	// 내 이름 가져오기
	@Override
	public String selectMyName(String username) {
		return mapper.selectMyName(username);
	}	
	
	// 내 상점 정보 등록하기
	@Override
	public void shopListInsert(ShopListDTO SLdto) {
		mapper.shopListInsert(SLdto);
	}
	
	// 내 상점 테이블 만들기
	@Override
	public void createProduct(String username) {
		mapper.createProduct(username);
	}
	
	// FINISH
	
	
	// TEST
	


	// 상품 등록하기
	@Override
	public void productInsert(AllProductDTO APdto) {
		mapper.productInsert(APdto);
	}

	// 상점 주소 가져오는 코드
	@Override
	public String selectAddress(String username) {
		return mapper.selectAddress(username);
	}	

	// area1 값 가져오는 코드
	@Override
	public int selectArea1(String area1Address) {
		return mapper.selectArea1(area1Address);
	}

	// area2 값 가져오는 코드
	@Override
	public int selectArea2(String area2Address, int area1) {
		return mapper.selectArea2(area1, area2Address);
	}
	
	// 상품 등록할 때 이미지 넣기
	@Override
	public void imagesInsert(ImagesDTO Idto) {
		mapper.imagesInsert(Idto);
	}

	// 상품 등록할 때 username_product에 옵션들 넣기
	@Override
	public void optionInsert(ProductDTO Pdto) {
		mapper.optionInsert(Pdto);
	}
	
	// 카테고리 대분류
	@Override
	public List<ProductCategoryDTO> selectCate1() {
		return mapper.selectCate1();
	}
	
	@Override
	public List<ProductCategoryDTO> selectCate2(int cate1) {
		return mapper.selectCate2(cate1);
	}

	@Override
	public List<ProductCategoryDTO> selectCate3(int cate1, int cate2) {
		return mapper.selectCate3(cate1, cate2);
	}

	@Override
	public List<AllProductDTO> selectAllProductLastProductNum(String username) {
		return mapper.selectAllProductLastProductNum(username);
	}
	
	// 내 상점 페이지에 필요한 정보들 가져오기
	@Override
	public ShopListDTO selectMyShop(String username) {
		return mapper.selectMyShop(username);
	}

	// 유저의 상품들 가져오기
	@Override
	public List<AllProductDTO> selectUsernameProduct(String username) {
		return mapper.selectUsernameProduct(username);
	}

	@Override
	public String sampleAddress(String username) {
		return mapper.sampleAddress(username);
	}
	
	
	// 상품 등록할 때 상품 리뷰 테이블 만들기
	@Override
	public void createReviews(String createReviewsProductnum) {
		mapper.createReviews(createReviewsProductnum);
	}



}


	/* 가장 최근의 상품번호값 없으면 카운트0 있으면 상품 수
	@Override
	public int selectLastProductNumCnt(String keyword, String username) {
		return mapper.selectLastProductNumCnt(keyword, username);
	}
	
	// 상품번호가 있으면 상품번호 뽑아오는거
	@Override
	public List<AllProductDTO> selectLastProductNum(String keyword) {
		return mapper.selectLastProductNum(keyword);
	}
	
	@Override
	public List<String> selectOptionNum(String keyword, String username) {
		return mapper.selectOptionNum(keyword, username);
	}	
	*/
