package nong.soon.bae.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.UsersDTO;

public interface ProductService {
	// ���� ����(���̺�) �����
	public void createProduct(String username);

	// ���� �̹���(���̺�) �����
	public void createImages(String username);
	
	// FINISH
	
	// �Ǹ� ���� �ֱ�
	public int productInsert(ProductDTO product, List<MultipartFile> files, String path);
	
	// TEST
	
	public List<ProductDTO> myProduct(String username);
	
	
	public void allproduct(AllProductDTO dto);
	
	// ���� �ֱٿ� ��ǰ����� productnum�� ��������
	public String selectProductnum(String username);
	
	public int imagesInsert(List<MultipartFile> files, String path, String username);
}
