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
	
	// 내 상점 정보 등록하기 
	@Override
	public void allShopList(ShopListDTO dto) {
		mapper.allShopList(dto);		
	}	
	
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
		
	// 상품 등록하기
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
	
	// 이미지 파일 넣기
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

	// 상품 옵션 추가
	@Override
	public void optionInsert(ProductDTO product) {
		mapper.optionInsert(product);
	}
	
	// 가장 최근에 상품등록한 productnum값 가져오기
	@Override
	public String selectProductnum(String username) {
		return mapper.selectProductnum(username);
	}
	
	// 전체 상품 테이블에 상품 등록할 때 넣을 유저의 주소 찾기
	@Override
	public List<AreaDTO> selectAddress(String username) {
		return mapper.selectAddress(username);
	}
	
	// 전체 상품 테이블에 상품 등록하기
	@Override
	public void allProductInsert(AllProductDTO dto) {
		mapper.allProductInsert(dto);
	}
	
	// 상품 등록할 때 상품 번호로 리뷰테이블 만들기
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
	
	// 상품 상세정보 보기
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

	// 상품 찜하기 유무
	@Override
	public int selectProductPickCount(String username, String productnum) {
		return mapper.selectProductPickCount(username, productnum);
	}	
	
	// 상품 찜하기
	@Override
	public void productPick(String username, String productnum) {
		mapper.productPick(username, productnum);
	}
	
	// 찜하기 누를 때마다 상품 찜 1씩 증가
	@Override
	public void updateProductWishcount(String username, String productnum) {
		mapper.updateProductWishcount(username, productnum);
	}

	// 상품 찜 삭제하기
	@Override
	public void productPickDelete(String username, String productnum) {
		mapper.productPickDelete(username, productnum);
	}
	
	// 찜 삭제하기 누를 때마다 상품 찜 1씩 감소
	@Override
	public void deleteProductWishcount(String username, String productnum) {
		mapper.deleteProductWishcount(username, productnum);
	}
	
	// 장바구니 상품 유무
	@Override
	public int selectProductShoppingCartCount(String username, String productnum) {
		return mapper.selectProductShoppingCartCount(username, productnum);
	}
	
	// 장바구니 상품 담기
	@Override
	public void productShoppingCart(String username, String productnum) {
		mapper.productShoppingCart(username, productnum);
	}
	
	// 장바구니 상품 삭제하기
	@Override
	public void productShoppingCartDelete(String username, String productnum) {
		mapper.productShoppingCartDelete(username, productnum);
	}


	



	
	
}
