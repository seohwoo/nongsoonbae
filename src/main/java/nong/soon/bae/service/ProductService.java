package nong.soon.bae.service;

import java.util.List;

import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.UsersDTO;

public interface ProductService {
	// ī�װ� ���� ��������
	public List<ProductCategoryDTO> selectProductcategory();
	
	// ���� ����(���̺�) �����
	public void createProduct(String username);
	
	// ���� ���� sequence �����
	public void createSEQ(String username);
	
	// FINISH
	
	// �Ǹ� ���� �ֱ�
	public void productInsert(ProductDTO product, String username);
	
	// TEST
	public List<ProductCategoryDTO> selectCate1();
	
	public List<ProductCategoryDTO> selectCate2();
	
	public List<ProductCategoryDTO> selectCate3();
}
