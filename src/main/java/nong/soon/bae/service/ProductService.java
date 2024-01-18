package nong.soon.bae.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;
import nong.soon.bae.bean.UsersDTO;

public interface ProductService {
	// �� ���� ���� ����ϱ� 
	public void allShopList(ShopListDTO dto);
	
	// ���� ����(���̺�) �����
	public void createProduct(String username);

	// ���� �̹���(���̺�) �����
	public void createImages(String username);
	
	// ��ǰ ����ϱ�
	public int productInsert(ProductDTO product, List<MultipartFile> files, String path);
	
	// �̹��� ���� �ֱ�
	public int imagesInsert(List<MultipartFile> files, String path, String username);
	
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
		
	// ��ǰ ������ ����
	public ProductDTO productDetail(String productnum, String username, String otherUsername);		
	
	// ��ǰ�ѹ��� ��������ã��
	public String selectUsername(String productnum);
	
	public AreaDTO selectArea(String productnum, String username, String otherUsername);
	
	public String selectAreaName1(AreaDTO areaDTO);
	
	public String selectAreaName2(AreaDTO areaDTO);
	
	public String selectName(String username);
	
	public List<ProductDTO> selectOption(String username, String optionstatus, String otherUsername);
	
	public List<AllProductDTO> allProduct();

	// ��ǰ ���ϱ� ����
	public int selectProductPickCount(String username, String productnum);	
		
	// ��ǰ ���ϱ�
	public void productPick(String username, String productnum, String otherUsername);

	// ���ϱ� ���� ������ ��ǰ �� 1�� ����
	public void updateProductWishcount(String otherUsername, String productnum);	

	// ��ǰ �� �����ϱ�
	public void productPickDelete(String username, String productnum);
	
	// �� �����ϱ� ���� ������ ��ǰ �� 1�� ����
	public void deleteProductWishcount(String otherUsername, String productnum);	
	
	// ��ٱ��� ��ǰ ����
	public int selectProductShoppingCartCount(String username, String productnum);
	
	// ��ٱ��� ��ǰ ���
	public void productShoppingCart(String username, String productnum, String otherUsername);	
	
	// ��ٱ��� ��ǰ �����ϱ�
	public void productShoppingCartDelete(String username, String productnum);
	
	// ��ǰ Ŭ���� ��ȸ�� ����
	public void updateReadcount(String username, String productnum);

	// ��ǰ ��ȸ�� ����
	public void productReadcountInsert(String username, String productnum);	
	
	// ���� ��ǰ ��ȸ�� ��� ã��
	public int todayProductReadcount(String username, String productnum, String todaydate);
	
	
	
	// TEST
	
	public List<ProductDTO> myProduct(String username);
	

	
	
	
	
	public ProductDTO productInfo(ProductDTO productDTO);
	
	
}
