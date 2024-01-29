 package nong.soon.bae.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ImagesDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;

public interface ProductMapper {	
	// �� �̸� ��������
	public String selectMyName(String username);
	
	// �� ���� ���� ����ϱ� 
	public void shopListInsert(ShopListDTO SLdto);
	
	// �� ���� ���̺� �����
	public void createProduct(String username);
	
	// ī�װ� ��з�
	public List<ProductCategoryDTO> selectCate1();
	
	// ī�װ� �ߺз�
	public List<ProductCategoryDTO> selectCate2(int cate1);
	
	// ī�װ� �Һз�
	public List<ProductCategoryDTO> selectCate3(@Param("cate1") int cate1, 
												@Param("cate2") int cate2);	
		
	// ��ǰ ����ϱ�
	public void productInsert(AllProductDTO APdto);
	
	// ��ǰ ����� ������ productnum ���ϱ�
	public List<AllProductDTO> selectAllProductLastProductNum(String username);	

	// ��ǰ ����� �� ��ǰ ���� ���̺� �����
	public void createReviews(String createReviewsProductnum);
	
	// ���� �ּ� �������� �ڵ�
	public String selectAddress(String username);
	
	// area1 �� �������� �ڵ�
	public int selectArea1(String area1Address);
	
	// area2 �� �������� �ڵ�
	public int selectArea2(@Param("area1") int area1, 
						   @Param("area2Address") String area2Address);
	
	// ��ǰ ����� �� �̹��� �ֱ�
	public void imagesInsert(ImagesDTO Idto);
	
	// ��ǰ ����� �� username_product�� �ɼǵ� �ֱ�
	public void optionInsert(ProductDTO Pdto);
	
	// FINISH
	
	// TEST

	// �� ���� �������� �ʿ��� ������ ��������
	public ShopListDTO selectMyShop(String username);
	
	// ������ ��ǰ�� ��������
	public List<AllProductDTO> selectUsernameProduct(String username);
	
	
	
	// sample
	public List<ProductDTO> sampleDetails(@Param("username") String username, 
										  @Param("productnum") String productnum);
}


	/* ���� �ֱ��� ��ǰ��ȣ�� ������ ī��Ʈ0 ������ ��ǰ ��
	//public int selectLastProductNumCnt(@Param("keyword") String keyword, 
	//		  						   @Param("username") String username);
	
	// ��ǰ��ȣ�� ������ ��ǰ��ȣ �̾ƿ��°�
	//public List<AllProductDTO> selectLastProductNum(String keyword);
	
	//public List<String> selectOptionNum(@Param("keyword") String keyword, 
								  @Param("username") String username);
	*/
