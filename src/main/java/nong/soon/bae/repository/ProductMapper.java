package nong.soon.bae.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.UsersDTO;

public interface ProductMapper {
	// ī�װ� ���� ��������
	public List<ProductCategoryDTO> selectProductcategory();
	
	// ���� ����(���̺�) �����
	public void createProduct(String username);
	
	// ���� ���� sequence �����
	public void createSEQ(String username);
	
	// FINISH
	
	// �Ǹ� ���� �ֱ�
	public int productInsert(ProductDTO product);
	
	// TEST
	public List<ProductCategoryDTO> selectCate1();
	
	public List<ProductCategoryDTO> selectCate2();
	
	public List<ProductCategoryDTO> selectCate3();
	
}
