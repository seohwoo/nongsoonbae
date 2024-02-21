 package nong.soon.bae.repository;

import java.util.HashMap;
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
import nong.soon.bae.bean.UsersDTO;

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
	// ī�װ� �Һз�
	public ProductCategoryDTO selectCate4(@Param("cate1") int cate1, 
			@Param("cate2") int cate2, @Param("cate3") int cate3);	
		
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
	
	//��ǰ������ ������
	public AllProductDTO findProductInfoToReadcnt(String productnum);
	
	
	
	
	
	
	
	//������������(����)
	String CheckMyShop(String username);
	
	// FINISH
	
	// TEST

	// ��ǰ ���� ������
	public AllProductDTO selectProductInfo(@Param("follow") String follow, 
										   @Param("productnum") String productnum);
	
	// ��ǰ �ø� ����� �ּ�, �̸�, �ȷο� ã��
	public AllProductDTO selectProductNameAddressFollowers(@Param("follow") String follow, 
														   @Param("productnum") String productnum);
	
	// ��ǰ �ɼǵ� ��������
	public List<ProductDTO> selectProductOptionAll(@Param("follow") String follow, 
			   									   @Param("productnum") String productnum);
	
	// ��ǰ ���� ��������
	public List<AllProductDTO> selectProductImagesAll(@Param("follow") String follow, 
													  @Param("productnum") String productnum);



	
	
	
	
	// TEST
	
	// ��ٱ��� ���
	public void insertShopping(MyPageDTO MPdto);
	
	
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
	public int reviewInsert(ReviewsDTO Rdto);
	
	// ���� ����� �� �̹��� �ֱ�
	public int ReviewsimagesInsert(@Param("filename") String filename, 
								   @Param("username") String username,
								   @Param("productnum") String productnum);
	

	// ��ǰ ���� ��������
	public List<ReviewsDTO> selectReviewsAll(@Param("follow") String follow, 
											 @Param("productnum") String productnum);
	
	// ��ǰ ���� ��
	public int selectReviewsCount(String productnum);
	
	// ��ǰ ��ü��� ����
	public List<AllProductDTO> selectAllproduct();
	
	// shoplist���� �� ���� ���� �����ϱ�
	public void deleteShoplist(String username);
	
	// productnum ������ ���� ���̺� �����ϱ�
	public void dropReviewsTable(String productnum);
	
	// username���� productnum ��������
	public List<String> selectUsernameProductnum(String username);
	
	public void allproductUpdateContent(AllProductDTO dto);
	

	public void deleteProductOption(@Param("username") String username, 
			   						@Param("productnum") String productnum);

	public void deleteAllproduct(@Param("username") String username, 
								 @Param("productnum") String productnum);

	
	// ��ǰ �ǸŽ� ��� ������Ʈ	
	public List<MyPageDTO> selectMypage3(String username);	
	public void updateProductCount(@Param("follow") String follow, 
			 					   @Param("cnt") int cnt,
			 					  @Param("optionnum") String optionnum);
	
	// ��ǰ ��ȸ�� ����
	public void updateReadcntPlus(String productnum);
	
	// ��ǰ ��ȸ�� �������� �ֱ�
	public void productReadcntInsert(@Param("username") String username, 
									 @Param("productnum") String productnum);

	// ���� ��ǰ ��ȸ�� ���� ã��
	public int selectTodayReadcntUsername(@Param("username") String username, 
			 							  @Param("productnum") String productnum, 
			 							  @Param("todaydate") String todaydate);

	// ���� ����� �� allproduct ��ǰ ����
	public void allproductDeleteMyUsername(String username);
	
	// ���� ����� �� username_Product ���
	public void dropUsernameProduct(String username);

	// ��ǰ ���� �� ��� ��������
	public List<ReviewsDTO> selectReviewsUsername(String productnum);
	
	// ���� �� ����� �������� ����
	public List<ReviewsDTO> ReviewsInfoFinal(@Param("productnum") String productnum, 
											 @Param("follow") String follow, 
											 @Param("usernames") String usernames,
											 @Param("formatdate") String formatdate);

	// ���� �����ϱ�
	public void myReviewsDelete(@Param("productnum") String productnum, 
			 					@Param("myName") String myName);
	
	
	public ShopListDTO findMyShopInfo(String username);
	
	public int findTodayPriceCnt(String username);
	
	public int findTodayPrice(String username);
	
	public int isUserSell(String username);
	
	public List<String> userSellDateList(String username);
	
	public int findSellDatePrice(HashMap<String, String> map);
	
	public ShopListDTO findByShopInfo(String username);
	
	public UsersDTO findRealUsername(String myName);

	public int findReviewImageCnt(HashMap<String, String> map);
	
	public List<ImagesDTO> findReviewImages(HashMap<String, String> map);
	
	public void deleteReviewImages(HashMap<String, String> map);
	
	   // ��ǰ ������ (grade 200)
	   public void updateProductGrade200(String productnum);
	   
	   // allProduct username , productnum
	   public List<AllProductDTO> allProductSelect();
	   
	   // ��ǰ ���� 0�̸� ��� 200
	   public void updateAllProductGrade200(@Param("productnum") String productnum,
	                               			@Param("usernames") String usernames);	
}
	
	

	/* ���� �ֱ��� ��ǰ��ȣ�� ������ ī��Ʈ0 ������ ��ǰ ��
	//public int selectLastProductNumCnt(@Param("keyword") String keyword, 
	//		  						   @Param("username") String username);
	
	// ��ǰ��ȣ�� ������ ��ǰ��ȣ �̾ƿ��°�
	//public List<AllProductDTO> selectLastProductNum(String keyword);
	
	//public List<String> selectOptionNum(@Param("keyword") String keyword, 
								  @Param("username") String username);
	*/
