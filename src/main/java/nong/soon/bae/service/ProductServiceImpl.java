package nong.soon.bae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ImagesDTO;
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
	
	// FINISH
	
	
	// TEST
	


	// ��ǰ ����ϱ�
	@Override
	public void productInsert(AllProductDTO APdto) {
		mapper.productInsert(APdto);
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
	
	// ���� �ֱ��� ��ǰ��ȣ�� ������ ī��Ʈ0 ������ ��ǰ ��
	@Override
	public int selectLastProductNumCnt(String keyword) {
		return mapper.selectLastProductNumCnt(keyword);
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











	
	
	

}
