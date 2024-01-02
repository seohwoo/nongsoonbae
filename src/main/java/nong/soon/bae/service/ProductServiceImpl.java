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
	
	// ī�װ� ���� ��������
	@Override
	public List<ProductCategoryDTO> selectProductcategory() {
		return mapper.selectProductcategory();
	}
	
	// ���� ����(���̺�) �����
	@Override
	public void createProduct(String username) {
		mapper.createProduct(username);
	}
	
	// ���� ���� sequence �����
	@Override
	public void createSEQ(String username) {
		mapper.createSEQ(username);
	}
	
	// FINISH
	
	// �Ǹ� ���� �ֱ�
	@Override
	public void productInsert(ProductDTO product, String username) {
		mapper.productInsert(product);
	}
	
}
