package nong.soon.bae.contorller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.service.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	@Autowired
	private ProductService service;
	
	// 개인 상점(테이블) 만들기
	@RequestMapping("createProduct")
	public String createProduct(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("username", username);
		return "product/createProduct";
	}
	
	// 개인 상점 (테이블) 만들기222
	@RequestMapping("createProductPro")
	public String createProductPro(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("username", username);
		service.createProduct(username);
		service.createSEQ(username);
		return "redirect:/product/productMain";
	}
	
	// 일단 상점 메인
	@RequestMapping("productMain")
	public String productMain(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("username", username);
		return "product/productMain";
	}
	
	// FINISH
	
	// 상품 등록
	@RequestMapping("productWriteForm")
	public String productWriteForm(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("username", username);
		return "product/productWriteForm";
	}
	
	@RequestMapping("productWritePro")
	public String productWritePro(Model model, Principal principal, ProductDTO product) {
		String username = principal.getName();
		model.addAttribute("username", username);
		
		service.productInsert(product, username);
		return "product/productWritePro";
	}
	
	
	@RequestMapping("sample")
	public String sample(Model model) {
		List<ProductCategoryDTO> categoryDTO = service.selectProductcategory();
		List<ProductCategoryDTO> cate1 = service.selectCate1();
		List<ProductCategoryDTO> cate2 = service.selectCate2();
		List<ProductCategoryDTO> cate3 = service.selectCate3();
		
		model.addAttribute("categoryDTO", categoryDTO);
		model.addAttribute("cate1", cate1);
		model.addAttribute("cate2", cate2);
		model.addAttribute("cate3", cate3);
		return "product/sample";
	}
	
	
}
