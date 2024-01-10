package nong.soon.bae.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.UsersDTO;

public interface ProductMapper {
	// ���� ����(���̺�) �����
	public void createProduct(String username);

	// ���� �̹���(���̺�) �����
	public void createImages(String username);
	
	// FINISH
	
	// �Ǹ� ���� �ֱ�
	public int productInsert(ProductDTO product);
	
	// TEST
	
	public List<ProductDTO> myProduct(String username);
	
	
	public void allproduct(AllProductDTO dto);
	
	// ���� �ֱٿ� ��ǰ����� productnum�� ��������
	public String selectProductnum(String username);
	
	// TEST0110
	public int imagesInsert(@Param("username") String username,
							@Param("filename") String filename);
	
}
