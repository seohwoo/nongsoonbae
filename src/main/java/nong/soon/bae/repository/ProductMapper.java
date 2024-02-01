 package nong.soon.bae.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ImagesDTO;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ReviewsDTO;
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
	
	//������������(����)
	String CheckMyShop(String username);
	
	// FINISH
	
	// TEST

	// ��ǰ ���� ������
	public AllProductDTO selectProductInfo(@Param("follow") String follow, 
										   @Param("productnum") String productnum);
	
	// ��ǰ �ø� ����� �ּ�, �̸�, �ȷο� ã��
	public AllProductDTO selectProductNameAddressFollowers(String follow);
	
	// ��ǰ �ɼǵ� ��������
	public List<ProductDTO> selectProductOptionAll(@Param("follow") String follow, 
			   									   @Param("productnum") String productnum);
	
	// ��ǰ ���� ��������
	public List<AllProductDTO> selectProductImagesAll(@Param("follow") String follow, 
													  @Param("productnum") String productnum);

	// ��ǰ ���ϱ�
	public void InsertProductPick(MyPageDTO MPdto);
	
	// ��ǰ�� �� +1 �ϱ�
	public void allproductWishcntPlus(String productnum);
	
	// ��ǰ �� ����
	public int selectPickCount(@Param("username") String username, 
							   @Param("productnum") String productnum);

	// ������������ ��ǰ �� �����ϱ�
	public void deleteProductPick(@Param("username") String username, 
								  @Param("productnum") String productnum);
	
	// Allproduct ��ǰ�� �� -1 �ϱ�
	public void allproductWishcntMinus(String productnum);
	
	// ��� �ȷο��ϱ�
	public void InsertUsernameFollow(MyPageDTO MPdto);	
	
	// ��� �ȷο��ϸ� userdetails�� followers +1 �ϱ�
	public void userdetailsUpdateFollowersPlus(String follow);
	
	// ���������� ��� ���� ����
	public int selectFollowCount(@Param("username") String username,
								 @Param("follow") String follow);
	
	// ��� �ȷο� ����ϱ�
	public void deleteFollow(@Param("username") String username,
						 	 @Param("follow") String follow);
	
	// ��� �ȷο� ����ϸ� userdetails�� followers -1 �ϱ�
	public void userdetailsUpdateFollowersMinus(String follow);
	
	
	
	
	// TEST
	
	
	
	
	// �� ���� �������� �ʿ��� ������ ��������
	public ShopListDTO selectMyShop(String username);
	
	// ������ ��ǰ�� ��������
	public List<AllProductDTO> selectUsernameProduct(String username);
	
	// �̸��̶� ��ǰ �������� �ڵ�
	public AllProductDTO selectAllProductPlusNameFollowers(String productnum);
	
	// ��ǰ ����� ���� area1 �ּ�
	public AreaDTO selectArea1Address(int area1);
	
	// ��ǰ ����� ���� area2 �ּ�
	public AreaDTO selectArea2Address(@Param("area1") int area1, 
									  @Param("area2") int area2);	
	
	// ��ǰ �ɼ� ��������
	public List<ProductDTO> selectProductOption(@Param("follow") String follow, 
												@Param("productnum") String productnum);
	
	// ��ǰ �̹��� �������� 
	public List<ImagesDTO> selectProductImages(@Param("follow") String follow, 
											   @Param("productnum") String productnum);

	// ��ǰ ���侲��
	public void reviewInsert(ReviewsDTO Rdto);
	
	
	// ��ǰ ��ü��� ����
	public List<AllProductDTO> selectAllproduct();
}
	
	

	/* ���� �ֱ��� ��ǰ��ȣ�� ������ ī��Ʈ0 ������ ��ǰ ��
	//public int selectLastProductNumCnt(@Param("keyword") String keyword, 
	//		  						   @Param("username") String username);
	
	// ��ǰ��ȣ�� ������ ��ǰ��ȣ �̾ƿ��°�
	//public List<AllProductDTO> selectLastProductNum(String keyword);
	
	//public List<String> selectOptionNum(@Param("keyword") String keyword, 
								  @Param("username") String username);
	*/
