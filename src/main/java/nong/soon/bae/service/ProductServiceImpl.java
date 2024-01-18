package nong.soon.bae.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.repository.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper mapper;
	
	// �� ���� ���� ����ϱ� 
	@Override
	public void allShopList(ShopListDTO dto) {
		mapper.allShopList(dto);		
	}	
	
	// ���� ����(���̺�) �����
	@Override
	public void createProduct(String username) {
		mapper.createProduct(username);
	}

	// ���� �̹���(���̺�) �����
	@Override
	public void createImages(String username) {
		mapper.createImages(username);
	}
		
	// ��ǰ ����ϱ�
	@Override
	public int productInsert(ProductDTO product, List<MultipartFile> filelist, String path) {
		int imagecount = 0;
		for (MultipartFile file : filelist) {
			if(!file.getOriginalFilename().equals("")) {
				imagecount++;
			}
		}
		product.setImagecount(imagecount);
		
		return mapper.productInsert(product);
	}
	
	// �̹��� ���� �ֱ�
	@Override
	public int imagesInsert(List<MultipartFile> files, String path, String username) {
		int result = 0;
		String productnum = mapper.selectProductnum(username);
		for (int i = 1; i <= files.size(); i++) {
			MultipartFile file = files.get(i-1);
			String filename = file.getOriginalFilename();
			if(!filename.equals("")) {
				String ext = filename.substring(filename.lastIndexOf("."));
				filename = "file_"+productnum+"_"+i+ext;
				File copy = new File(path+filename);
				result = mapper.imagesInsert(productnum, filename, username);
				try {
					file.transferTo(copy);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}		

	// ��ǰ �ɼ� �߰�
	@Override
	public void optionInsert(ProductDTO product) {
		mapper.optionInsert(product);
	}
	
	// ���� �ֱٿ� ��ǰ����� productnum�� ��������
	@Override
	public String selectProductnum(String username) {
		return mapper.selectProductnum(username);
	}
	
	// ��ü ��ǰ ���̺� ��ǰ ����� �� ���� ������ �ּ� ã��
	@Override
	public List<AreaDTO> selectAddress(String username) {
		return mapper.selectAddress(username);
	}
	
	// ��ü ��ǰ ���̺� ��ǰ ����ϱ�
	@Override
	public void allProductInsert(AllProductDTO dto) {
		mapper.allProductInsert(dto);
	}
	
	// ��ǰ ����� �� ��ǰ ��ȣ�� �������̺� �����
	@Override
	public void createReviews(String productnum) {
		mapper.createReviews(productnum);
	}
	
	// FINISH
	
	
	
	@Override
	public List<AllProductDTO> allProduct() {
		return mapper.allProduct();
	}	
	
	// TEST
	
	@Override
	public List<ProductDTO> myProduct(String username) {
		return mapper.myProduct(username);
	}


	@Override
	public ProductDTO productInfo(ProductDTO productDTO) {
		return mapper.productInfo(productDTO);
	}
	
	// ��ǰ ������ ����
	@Override
	public ProductDTO productDetail(String productnum, String username) {
		return mapper.productDetail(productnum, username);
	}

	@Override
	public AreaDTO selectArea(String productnum, String username) {
		return mapper.selectArea(productnum, username);
	}

	@Override
	public String selectAreaName1(AreaDTO areaDTO) {
		return mapper.selectAreaName1(areaDTO);
	}

	@Override
	public String selectAreaName2(AreaDTO areaDTO) {
		return mapper.selectAreaName2(areaDTO);
	}

	@Override
	public String selectName(String username) {
		return mapper.selectName(username);
	}

	@Override
	public List<ProductDTO> selectOption(String username, String optionstatus) {
		return mapper.selectOption(username, optionstatus);
	}

	// ��ǰ ���ϱ� ����
	@Override
	public int selectProductPickCount(String username, String productnum) {
		return mapper.selectProductPickCount(username, productnum);
	}	
	
	// ��ǰ ���ϱ�
	@Override
	public void productPick(String username, String productnum) {
		mapper.productPick(username, productnum);
	}
	
	// ���ϱ� ���� ������ ��ǰ �� 1�� ����
	@Override
	public void updateProductWishcount(String username, String productnum) {
		mapper.updateProductWishcount(username, productnum);
	}

	// ��ǰ �� �����ϱ�
	@Override
	public void productPickDelete(String username, String productnum) {
		mapper.productPickDelete(username, productnum);
	}
	
	// �� �����ϱ� ���� ������ ��ǰ �� 1�� ����
	@Override
	public void deleteProductWishcount(String username, String productnum) {
		mapper.deleteProductWishcount(username, productnum);
	}
	
	// ��ٱ��� ��ǰ ����
	@Override
	public int selectProductShoppingCartCount(String username, String productnum) {
		return mapper.selectProductShoppingCartCount(username, productnum);
	}
	
	// ��ٱ��� ��ǰ ���
	@Override
	public void productShoppingCart(String username, String productnum) {
		mapper.productShoppingCart(username, productnum);
	}
	
	// ��ٱ��� ��ǰ �����ϱ�
	@Override
	public void productShoppingCartDelete(String username, String productnum) {
		mapper.productShoppingCartDelete(username, productnum);
	}


	



	
	
}
