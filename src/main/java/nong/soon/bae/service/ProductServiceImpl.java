package nong.soon.bae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.repository.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper mapper;
	
	// 카테고리 정보 가져오기
	@Override
	public List<ProductCategoryDTO> selectProductcategory() {
		return mapper.selectProductcategory();
	}
	
	// 개인 상점(테이블) 만들기
	@Override
	public void createProduct(String username) {
		mapper.createProduct(username);
	}
	
	// 개인 상점 sequence 만들기
	@Override
	public void createSEQ(String username) {
		mapper.createSEQ(username);
	}
	
	// FINISH
	
	// 판매 정보 넣기
	@Override
	public void productInsert(ProductDTO product, String username) {
		mapper.productInsert(product);
	}
	
}
