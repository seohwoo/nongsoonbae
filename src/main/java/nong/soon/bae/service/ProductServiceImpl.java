package nong.soon.bae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ImagesDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
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
	
	// ������ ���� �ּ� �������� �ڵ�
	@Override
	public List<AreaDTO> selectArea(String username) {
		return mapper.selectArea(username);
	}

	// ��ǰ ����ϱ�
	@Override
	public void productInsert(AllProductDTO APdto) {
		mapper.productInsert(APdto);
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

	// ��ǰ ����� �� �̹��� �ֱ�
	@Override
	public void imagesInsert(ImagesDTO Idto) {
		mapper.imagesInsert(Idto);
	}

	
	
	

}
