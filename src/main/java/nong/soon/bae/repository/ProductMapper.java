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
	// �� ���� ���� ����ϱ� 
	public void allShopList(ShopListDTO dto);
	
	// ���� ����(���̺�) �����
	public void createProduct(String username);

	// ���� �̹���(���̺�) �����
	public void createImages(String username);
	
	// ��ǰ ����ϱ�
	public int productInsert(ProductDTO product);	
	
	// �̹��� ���� �ֱ�
	public int imagesInsert(@Param("productnum") String productnum,
							@Param("filename") String filename,
							@Param("username") String username);
	
	// ��ǰ �ɼ� �߰�
	public void optionInsert(ProductDTO product);
	
	// ���� �ֱٿ� ��ǰ����� productnum�� ��������
	public String selectProductnum(String username);
		
	// ��ü ��ǰ ���̺� ��ǰ ����� �� ���� ������ �ּ� ã��
	public List<AreaDTO> selectAddress(String username);
	
	// ��ü ��ǰ ���̺� ��ǰ ����ϱ�
	public void allProductInsert(AllProductDTO dto);	
	
	// ��ǰ ����� �� ��ǰ ��ȣ�� �������̺� �����
	public void createReviews(String productnum);	
	
	// FINISH
	
	public List<AllProductDTO> allProduct();
	
	// ��ǰ ������ ����
	public ProductDTO productDetail(@Param("productname") String productname,
								    @Param("username") String username);
	// TEST
	
	public List<ProductDTO> myProduct(String username);
	
	
	public ProductDTO productInfo(ProductDTO productDTO);
	
}
