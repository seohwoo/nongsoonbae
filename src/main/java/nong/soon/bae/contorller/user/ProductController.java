package nong.soon.bae.contorller.user;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.service.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService service;

	// 일단 상점 메인
	@RequestMapping("productMain")
	public String productMain(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("username", username);
		return "product/productMain";
	}	
	
	// 개인 상점(테이블) 만들기
	@RequestMapping("createProduct")
	public String createProduct(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("username", username);
		return "product/createProduct";
	}
	
	// 내 상점 정보 등록하기, 개인 상점, 이미지 테이블 만들기
	@RequestMapping("createProductPro")
	public String createProductPro(Model model, Principal principal, ShopListDTO dto) {
		String username = principal.getName();
		model.addAttribute("username", username);
		dto.setUsername(username);
		
		service.allShopList(dto);
		service.createProduct(username);
		service.createImages(username);
		return "redirect:/product/productMain";
	}
	
	// 상품 등록하기
	@RequestMapping("productWriteForm")
	public String productWriteForm(HttpServletRequest request, Model model, Principal principal, 
								   @RequestParam(value="productnum", defaultValue="0")String productnum) {
		
		String username = principal.getName();
		model.addAttribute("username", username);
		model.addAttribute("productnum", productnum);
		return "product/productWriteForm";
	}
	
	// 상품 등록하기
	@RequestMapping("productWritePro")
	public String productWritePro(HttpServletRequest request, List<MultipartFile> files, Model model, 
								  Principal principal, ProductDTO product, AllProductDTO dto, String cate3, 
								  @RequestParam("optionname") String[] optionname, 
								  @RequestParam("optiontotalprice") int[] optiontotalprice,
								  @RequestParam("optionProductCount") int[] optionProductCount) {
		
		String productnum = "";
		String username = principal.getName();
		
		product.setUsername(username);
		product.setProductnum(cate3); 		
		product.setSeqnum("C_"+cate3 );	
		String path = request.getServletContext().getRealPath("/resources/file/product/");
		
		// 상품재고수 다 더한 코드	
		int count = Arrays.stream(optionProductCount).sum();
		product.setProductcount(count);
		
		// 상품 추가된 옵션값 구한 코드
		int optionstatus = optionname.length;
		product.setOptionstatus(optionstatus);
		
		// 판매자가 입력한 가격 중 최저가격 구하기
		int minValue = Arrays.stream(optiontotalprice).min().orElse(0);
		product.setTotalprice(minValue);
		
		int cnt = service.productInsert(product, files, path);
		int result = service.imagesInsert(files, path, username);
		
		// cate3 값을 cate1,2,3 으로 나눠서 AllproductDTO 에 넣는 코드
		int catego1 = cate3.charAt(0)-48;
		int catego2 = cate3.charAt(1)-48;
		int catego3 = cate3.charAt(2)-48;				
		dto.setCate1(catego1);
		dto.setCate2(catego2);
		dto.setCate3(catego3);
		
		// 상점의 주소를 AllProduct 테이블에 넣기
		List<AreaDTO> selectAddress = service.selectAddress(username);
		for (AreaDTO area : selectAddress) {
            if (area.getArea1() >= 1 && area.getArea2() >= 1) {
            	dto.setArea1(area.getArea1());
            	dto.setArea2(area.getArea2());
            }
        }
 	
		// 가장 최근에 등록한 productnum값 allproduct에 넣기
		if(cnt >0) {
			dto.setProductnum(service.selectProductnum(username));
			service.allProductInsert(dto);
		}
		
		// 옵션 값만큼 상품 등록하기 
		for (int i = 0; i < optionname.length; i++) {
		    product.setProductname(optionname[i]);
		    product.setTotalprice(optiontotalprice[i]);
		    product.setProductcount(optionProductCount[i]);
		    product.setProductnum(cate3);
		    service.optionInsert(product);		    
		}		
		
		// productnum값으로 리뷰 테이블 만들기
		productnum = "P_"+service.selectProductnum(username);
		service.createReviews(productnum);
		
		model.addAttribute("username", username);
		model.addAttribute("cnt", cnt);
		model.addAttribute("result", result);		
		
		return "product/productWritePro";
	}
	 
	// FINISH
	
	// TEST
	
	@RequestMapping("myProduct")
	public String myProduct(Model model, Principal principal, ProductDTO dto) {
		String username = principal.getName();
		model.addAttribute("username", username);
		dto.setUsername(username);
		List<ProductDTO> productDTO = service.myProduct(username);
		model.addAttribute("productDTO", productDTO);
		return "product/myProduct";
	}
	
	@RequestMapping("productInfo")
	public String productInfo(Model model, Principal principal, ProductDTO productDTO, String productnum) {
		String username = principal.getName();
		model.addAttribute("username", username);
		productDTO.setUsername(username);
		productDTO = service.productInfo(productDTO);
		model.addAttribute("productDTO", productDTO);
		return "product/productInfo";
	}
	
	// 상품 상세보기 
	@RequestMapping("productDetail")
	public String productDetail(String productname, Model model, Principal principal, ProductDTO productDTO) {
		String username = principal.getName();
		model.addAttribute("username", username);
		productDTO.setUsername(username);
		
		productDTO = service.productDetail(productname, username);
		model.addAttribute("productDTO", productDTO);
		
		logger.info("DTO : "+productDTO);
		return "product/productDetail";
	}
	
	@RequestMapping("allProduct")
	public String allProduct(Model model, Principal principal, String productName) {
		String username = principal.getName();
		model.addAttribute("username", username);
		
		List<AllProductDTO> allProductDTO = service.allProduct();
		model.addAttribute("allProductDTO", allProductDTO);
		return "product/allProduct";
	}
	
	
	@PostMapping("sample2")
	public String sample2(@RequestParam("files") List<MultipartFile> files) {
		return "product/sample2";
	}
						
	@RequestMapping("sample")
	public String sample() {
		return "product/sample";
	}
	

	
	
}

