package nong.soon.bae.service;

import java.util.List;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ImagesDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ShopListDTO;

public interface ProductService {
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
	
	// ������ ���� �ּ� �������� �ڵ�
	public List<AreaDTO> selectArea(String username);	
	
	// ���� �ֱ��� ��ǰ��ȣ�� ������ ī��Ʈ0 ������ ��ǰ ��
	public int selectLastProductNumCnt(String keyword);
	
	// ��ǰ��ȣ�� ������ ��ǰ��ȣ �̾ƿ��°�
	public List<AllProductDTO> selectLastProductNum(String keyword);
	
	// ��ǰ ����� �� �̹��� �ֱ�
	public void imagesInsert(ImagesDTO Idto);
}
