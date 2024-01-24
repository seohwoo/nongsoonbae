package nong.soon.bae.service;

import java.util.List;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ImagesDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ShopListDTO;

public interface ProductService {
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
	
	// 상점에 대한 주소 가져오는 코드
	public List<AreaDTO> selectArea(String username);	
	
	// 가장 최근의 상품번호값 없으면 카운트0 있으면 상품 수
	public int selectLastProductNumCnt(String keyword);
	
	// 상품번호가 있으면 상품번호 뽑아오는거
	public List<AllProductDTO> selectLastProductNum(String keyword);
	
	// 상품 등록할 때 이미지 넣기
	public void imagesInsert(ImagesDTO Idto);
}
