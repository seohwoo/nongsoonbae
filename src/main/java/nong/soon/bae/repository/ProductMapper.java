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

	// ��ǰ�ѹ��� ��������ã��
	public String selectUsername(String productnum);
	
	// ��ǰ ������ ����
	public ProductDTO productDetail(@Param("productnum") String productnum,
								    @Param("username") String username,
									@Param("otherUsername") String otherUsername);
	
	public AreaDTO selectArea(@Param("productnum") String productnum,
							  @Param("username") String username,
							  @Param("otherUsername") String otherUsername);
	
	public String selectAreaName1(AreaDTO areaDTO);
	
	public String selectAreaName2(AreaDTO areaDTO);
	
	public String selectName(String username);
	
	public List<ProductDTO> selectOption(@Param("username") String username,
										 @Param("optionstatus") String optionstatus,
										 @Param("otherUsername") String otherUsername);

	// ��ǰ ���ϱ� ����
	public int selectProductPickCount(@Param("username") String username, 
			   						  @Param("productnum") String productnum);	
	
	// ��ǰ ���ϱ�
	public void productPick(@Param("username") String username, 
							@Param("productnum") String productnum,
							@Param("otherUsername") String otherUsername);

	// ���ϱ� ���� ������ ��ǰ �� 1�� ����
	public void updateProductWishcount(@Param("otherUsername") String otherUsername, 
									   @Param("productnum") String productnum);	
	
	// ��ǰ �� �����ϱ�
	public void productPickDelete(@Param("username") String username, 
								  @Param("productnum") String productnum);
	
	// �� �����ϱ� ���� ������ ��ǰ �� 1�� ����
	public void deleteProductWishcount(@Param("otherUsername") String otherUsername, 
									   @Param("productnum") String productnum);
	
	// ��ٱ��� ��ǰ ����
	public int selectProductShoppingCartCount(@Param("username") String username, 
											  @Param("productnum") String productnum);
	
	// ��ٱ��� ��ǰ ���
	public void productShoppingCart(@Param("username") String username, 
									@Param("productnum") String productnum,
									@Param("otherUsername") String otherUsername);
	
	// ��ٱ��� ��ǰ �����ϱ�
	public void productShoppingCartDelete(@Param("username") String username, 
			  							  @Param("productnum") String productnum);	
	
	// ��ǰ Ŭ���� ��ȸ�� ����
	public void updateReadcount(@Param("username") String username, 
								@Param("productnum") String productnum);
	
	// ��ǰ ��ȸ�� ����
	public void productReadcountInsert(@Param("username") String username, 
									   @Param("productnum") String productnum);
	
	// ���� ��ǰ ��ȸ�� ��� ã��
	public int todayProductReadcount(@Param("username") String username, 
									 @Param("productnum") String productnum,
									 @Param("todaydate") String todaydate);
	
	// TEST
	
	public List<ProductDTO> myProduct(String username);
	
	
	public ProductDTO productInfo(ProductDTO productDTO);
	
}
