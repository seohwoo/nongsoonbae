package nong.soon.bae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ImagesDTO;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;
import nong.soon.bae.repository.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper mapper;

	// �� �̸� ��������
	@Override
	public String selectMyName(String username) {
		return mapper.selectMyName(username);
	}	
	
	// �� ���� ���� ����ϱ�
	@Override
	public void shopListInsert(ShopListDTO SLdto) {
		mapper.shopListInsert(SLdto);
	}
	
	// �� ���� ���̺� �����
	@Override
	public void createProduct(String username) {
		mapper.createProduct(username);
	}

	// ī�װ� ��з�
	@Override
	public List<ProductCategoryDTO> selectCate1() {
		return mapper.selectCate1();
	}

	// ī�װ� �ߺз�	
	@Override
	public List<ProductCategoryDTO> selectCate2(int cate1) {
		return mapper.selectCate2(cate1);
	}

	// ī�װ� �Һз�
	@Override
	public List<ProductCategoryDTO> selectCate3(int cate1, int cate2) {
		return mapper.selectCate3(cate1, cate2);
	}	
	
	// ��ǰ ����ϱ�
	@Override
	public void productInsert(AllProductDTO APdto) {
		mapper.productInsert(APdto);
	}

	// ��ǰ ����� ������ productnum ���ϱ�
	@Override
	public List<AllProductDTO> selectAllProductLastProductNum(String username) {
		return mapper.selectAllProductLastProductNum(username);
	}	

	// ��ǰ ����� �� ��ǰ ���� ���̺� �����
	@Override
	public void createReviews(String createReviewsProductnum) {
		mapper.createReviews(createReviewsProductnum);
	}	
	
	// ���� �ּ� �������� �ڵ�
	@Override
	public String selectAddress(String username) {
		return mapper.selectAddress(username);
	}	

	// area1 �� �������� �ڵ�
	@Override
	public int selectArea1(String area1Address) {
		return mapper.selectArea1(area1Address);
	}

	// area2 �� �������� �ڵ�
	@Override
	public int selectArea2(String area2Address, int area1) {
		return mapper.selectArea2(area1, area2Address);
	}
	
	// ��ǰ ����� �� �̹��� �ֱ�
	@Override
	public void imagesInsert(ImagesDTO Idto) {
		mapper.imagesInsert(Idto);
	}

	// ��ǰ ����� �� username_product�� �ɼǵ� �ֱ�
	@Override
	public void optionInsert(ProductDTO Pdto) {
		mapper.optionInsert(Pdto);
	}
	
	// FINISH
	
	// TEST

	// ��ǰ ���� ������
	@Override
	public AllProductDTO selectProductInfo(String follow, String productnum) {
		return mapper.selectProductInfo(follow, productnum);
	}	
	
	// ��ǰ �ø� ����� �ּ�, �̸�, �ȷο� ã��	
	@Override
	public AllProductDTO selectProductNameAddressFollowers(String follow) {
		return mapper.selectProductNameAddressFollowers(follow);
	}
	
	// ��ǰ �ɼǵ� ��������
	@Override
	public List<ProductDTO> selectProductOptionAll(String follow, String productnum) {
		return mapper.selectProductOptionAll(follow, productnum);
	}	
	
	// ��ǰ ���� ��������
	@Override
	public List<AllProductDTO> selectProductImagesAll(String follow, String productnum) {
		return mapper.selectProductImagesAll(follow, productnum);
	}	

	// ��ǰ ���ϱ�
	@Override
	public void InsertProductPick(MyPageDTO MPdto) {
		mapper.InsertProductPick(MPdto);
	}

	// ��ǰ�� �� +1 �ϱ�
	@Override
	public void allproductWishcntPlus(String productnum) {
		mapper.allproductWishcntPlus(productnum);
	}
	
	// ��ǰ �� ����
	@Override
	public int selectPickCount(String username, String productnum) {
		return mapper.selectPickCount(username, productnum);
	}

	// ������������ ��ǰ �� �����ϱ�
	@Override
	public void deleteProductPick(String username, String productnum) {
		mapper.deleteProductPick(username, productnum);
	}

	// Allproduct ��ǰ�� �� -1 �ϱ�
	@Override
	public void allproductWishcntMinus(String productnum) {
		mapper.allproductWishcntMinus(productnum);
	}

	// ��� �ȷο��ϱ�
	@Override
	public void InsertUsernameFollow(MyPageDTO MPdto) {
		mapper.InsertUsernameFollow(MPdto);
	}	
	
	
	
	// TEST
	
	
	
	
	
	// �� ���� �������� �ʿ��� ������ ��������
	@Override
	public ShopListDTO selectMyShop(String username) {
		return mapper.selectMyShop(username);
	}

	// ������ ��ǰ�� ��������
	@Override
	public List<AllProductDTO> selectUsernameProduct(String username) {
		return mapper.selectUsernameProduct(username);
	}

	// �̸��̶� ��ǰ �������� �ڵ�
	@Override
	public AllProductDTO selectAllProductPlusNameFollowers(String productnum) {
		return mapper.selectAllProductPlusNameFollowers(productnum);
	}

	// ��ǰ ����� ���� area1 �ּ�	
	@Override
	public AreaDTO selectArea1Address(int area1) {
		return mapper.selectArea1Address(area1);
	}

	// ��ǰ ����� ���� area2 �ּ�
	@Override
	public AreaDTO selectArea2Address(int area1, int area2) {
		return mapper.selectArea2Address(area1, area2);
	}

	// ��ǰ �ɼ� ��������	
	@Override
	public List<ProductDTO> selectProductOption(String follow, String productnum) {
		return mapper.selectProductOption(follow, productnum);
	}
	
	// ��ǰ �̹��� ��������
	@Override
	public List<ImagesDTO> selectProductImages(String follow, String productnum) {
		return mapper.selectProductImages(follow, productnum);
	}



	// ��ǰ ��ü��� ����	
	@Override
	public List<AllProductDTO> selectAllproduct() {
		return mapper.selectAllproduct();
	}









	@Override
	public String CheckMyShop(String username) {
		return mapper.CheckMyShop(username);
	}



	
	




}


	/* ���� �ֱ��� ��ǰ��ȣ�� ������ ī��Ʈ0 ������ ��ǰ ��
	@Override
	public int selectLastProductNumCnt(String keyword, String username) {
		return mapper.selectLastProductNumCnt(keyword, username);
	}
	
	// ��ǰ��ȣ�� ������ ��ǰ��ȣ �̾ƿ��°�
	@Override
	public List<AllProductDTO> selectLastProductNum(String keyword) {
		return mapper.selectLastProductNum(keyword);
	}
	
	@Override
	public List<String> selectOptionNum(String keyword, String username) {
		return mapper.selectOptionNum(keyword, username);
	}	
	*/
