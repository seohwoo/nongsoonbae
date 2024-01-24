package nong.soon.bae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ImagesDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
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
	
	// 상점에 대한 주소 가져오는 코드
	@Override
	public List<AreaDTO> selectArea(String username) {
		return mapper.selectArea(username);
	}

	// 상품 등록하기
	@Override
	public void productInsert(AllProductDTO APdto) {
		mapper.productInsert(APdto);
	}

	// 가장 최근의 상품번호값 없으면 카운트0 있으면 상품 수
	@Override
	public int selectLastProductNumCnt(String keyword) {
		return mapper.selectLastProductNumCnt(keyword);
	}
	
	// 상품번호가 있으면 상품번호 뽑아오는거
	@Override
	public List<AllProductDTO> selectLastProductNum(String keyword) {
		return mapper.selectLastProductNum(keyword);
	}

	// 상품 등록할 때 이미지 넣기
	@Override
	public void imagesInsert(ImagesDTO Idto) {
		mapper.imagesInsert(Idto);
	}

	
	
	

}
