package nong.soon.bae.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ImagesDTO;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ReviewsDTO;
import nong.soon.bae.bean.ShopListDTO;

public interface ProductService {
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
	public List<ProductCategoryDTO> selectCate3(int cate1, int cate2);	
	
	public ProductCategoryDTO selectCate4(int cate1,int cate2,int cate3);
	
	//��ǰ������ ������
	public AllProductDTO findProductInfoToReadcnt(String productnum);
	
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
	public int selectArea2(String area2Address, int area1);
	
	// ��ǰ ����� �� �̹��� �ֱ�
	public void imagesInsert(ImagesDTO Idto);
	
	// ��ǰ ����� �� username_product�� �ɼǵ� �ֱ�
	public void optionInsert(ProductDTO Pdto);

	// ��ǰ ���ϱ�
	public void InsertProductPick(MyPageDTO MPdto);
	
	// ��ǰ�� �� +1 �ϱ�
	public void allproductWishcntPlus(String productnum);
	
	// ��ǰ �� ����
	public int selectPickCount(String username, String productnum);
	
	// ������������ ��ǰ �� �����ϱ�
	public void deleteProductPick(String username, String productnum);
	
	// Allproduct ��ǰ�� �� -1 �ϱ�
	public void allproductWishcntMinus(String productnum);	
	
	// ��� �ȷο��ϱ�
	public void InsertUsernameFollow(MyPageDTO MPdto);
	
	// ��� �ȷο��ϸ� userdetails�� followers +1 �ϱ�
	public void userdetailsUpdateFollowersPlus(String follow);	
	
	// ���������� ��� ���� ����
	public int selectFollowCount(String username, String follow);
	
	// ��� �ȷο� ����ϱ�
	public void deleteFollow(String username, String follow);
	
	// ��� �ȷο� ����ϸ� userdetails�� followers -1 �ϱ�
	public void userdetailsUpdateFollowersMinus(String follow);		
	
	
	
	
	
	
	
	
	
	
	String CheckMyShop(String username);
	
	// FINISH
	
	// TEST
	
	// ��ǰ ���� ������
	public AllProductDTO selectProductInfo(String follow, String productnum);
	
	// ��ǰ �ø� ����� �ּ�, �̸�, �ȷο� ã��
	public AllProductDTO selectProductNameAddressFollowers(String follow, String productnum);

	// ��ǰ �ɼǵ� ��������
	public List<ProductDTO> selectProductOptionAll(String follow, String productnum);
	
	// ��ǰ ���� ��������
	public List<AllProductDTO> selectProductImagesAll(String follow, String productnum);

	
	
	
	
	
	
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
	public AreaDTO selectArea2Address(int area1, int area2);
	
	// ��ǰ �ɼ� ��������
	public List<ProductDTO> selectProductOption(String follow, String productnum);

	// ��ǰ �̹��� �������� 	
	public List<ImagesDTO> selectProductImages(String follow, String productnum); 

	// ��ǰ ���侲��
	public int reviewInsert(List<MultipartFile> files, ReviewsDTO Rdto, String path);
	
	// ���� ����� �� �̹��� �ֱ�
	public int ReviewsimagesInsert(List<MultipartFile> files, String path, String username, String productnum);
	
	// ��ǰ ���� ��������
	public List<ReviewsDTO> selectReviewsAll(String follow, String productnum);
	
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


	public void deleteProductOption(String username, String productnum);
	
	public void deleteAllproduct(String username, String productnum);
	
	// ��ǰ �ǸŽ� ��� ������Ʈ
	public List<MyPageDTO> selectMypage3(String username);
	
	public void updateProductCount(String follow, int cnt, String optionnum);
	
	// ��ǰ ��ȸ�� ����
	public void updateReadcntPlus(String productnum);
	
	// ��ǰ ��ȸ�� �������� �ֱ�
	public void productReadcntInsert(String username, String productnum);
	
	// ���� ��ǰ ��ȸ�� ���� ã��
	public int selectTodayReadcntUsername(String username, String productnum, String todaydate);

	// ���� ����� �� allproduct ��ǰ ����
	public void allproductDeleteMyUsername(String username);
	
	// ���� ����� �� username_Product ���
	public void dropUsernameProduct(String username);
	
	// ��ǰ ���� �� ��� ��������
	public List<ReviewsDTO> selectReviewsUsername(String productnum);
	
	// ���� �� ����� �������� ����
	public List<ReviewsDTO> ReviewsInfoFinal(String productnum, String follow, String usernames, String formatdate);

	// ���� �����ϱ�
	public void myReviewsDelete(String productnum, String myName);
	
	/** ���� �������� Ȯ���ϱ� */
	public void findMyShopInfo(Model model, String username);
	
	/** ���� �Ǹų��� ��Ʈ Ȯ���ϱ� */
	public void findMySellChart(Model model, String username);
	
	public void deleteReviewImages(String productnum, String myName, String path);
	
}


	/* ���� �ֱ��� ��ǰ��ȣ�� ������ ī��Ʈ0 ������ ��ǰ ��
	//public int selectLastProductNumCnt(String keyword, String username);
	
	// ��ǰ��ȣ�� ������ ��ǰ��ȣ �̾ƿ��°�
	//public List<AllProductDTO> selectLastProductNum(String keyword);
	
	//public List<String> selectOptionNum(String keyword, String username);
	*/
