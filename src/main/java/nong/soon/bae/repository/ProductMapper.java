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
	
	// FINISH
	
	// TEST
	
	
	// ��ǰ ����ϱ�
	public void productInsert(AllProductDTO APdto);
	
	// ���� �ּ� �������� �ڵ�
	public String selectAddress(String username);
	
	// area1 �� �������� �ڵ�
	public int selectArea1(String area1Address);
	
	// area2 �� �������� �ڵ�
	public int selectArea2(@Param("area1") int area1, 
						   @Param("area2Address") String area2Address);
	
	// ���� �ֱ��� ��ǰ��ȣ�� ������ ī��Ʈ0 ������ ��ǰ ��
	public int selectLastProductNumCnt(String keyword);
	
	// ��ǰ��ȣ�� ������ ��ǰ��ȣ �̾ƿ��°�
	public List<AllProductDTO> selectLastProductNum(String keyword);
	
	public List<String> selectOptionNum(@Param("keyword") String keyword, 
								  @Param("username") String username);
	
	// ��ǰ ����� �� �̹��� �ֱ�
	public void imagesInsert(ImagesDTO Idto);
	
	// ��ǰ ����� �� username_product�� �ɼǵ� �ֱ�
	public void optionInsert(ProductDTO Pdto);
	
}
