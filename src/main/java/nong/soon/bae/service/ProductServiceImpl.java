package nong.soon.bae.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.repository.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper mapper;
	
	// 개인 상점(테이블) 만들기
	@Override
	public void createProduct(String username) {
		mapper.createProduct(username);
	}

	// 개인 이미지(테이블) 만들기
	@Override
	public void createImages(String username) {
		mapper.createImages(username);
	}
	
	// FINISH
	
	// 판매 정보 넣기
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


	@Override
	public List<ProductDTO> myProduct(String username) {
		return mapper.myProduct(username);
	}


	@Override
	public void allproduct(AllProductDTO dto) {
		mapper.allproduct(dto);
	}
	
	// 가장 최근에 상품등록한 productnum값 가져오기
	@Override
	public String selectProductnum(String username) {
		return mapper.selectProductnum(username);
	}

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

	@Override
	public void createReviews(String productnum) {
		mapper.createReviews(productnum);
	}

	@Override
	public ProductDTO productInfo(ProductDTO productDTO) {
		return mapper.productInfo(productDTO);
	}

	

	
	
}
