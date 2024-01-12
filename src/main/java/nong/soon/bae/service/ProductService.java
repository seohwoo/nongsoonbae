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
	
	// ��ǰ ����ϱ�
	public int productInsert(ProductDTO product, List<MultipartFile> files, String path);
	
	// �̹��� ���� �ֱ�
	public int imagesInsert(List<MultipartFile> files, String path, String username);
	
	// ��ǰ �ɼ� �߰�
	public void optionInsert(ProductDTO product);
	
	// ���� �ֱٿ� ��ǰ����� productnum�� ��������
	public String selectProductnum(String username);
	
	// ��ü ��ǰ ���̺� ��ǰ ����ϱ�
	public void allproduct(AllProductDTO dto);
	
	// ��ǰ ����� �� ��ǰ ��ȣ�� �������̺� �����
	public void createReviews(String productnum);
	
	// FINISH
	
	
	
	// TEST
	
	public List<ProductDTO> myProduct(String username);
	

	
	
	
	
	public ProductDTO productInfo(ProductDTO productDTO);
	
	
}
