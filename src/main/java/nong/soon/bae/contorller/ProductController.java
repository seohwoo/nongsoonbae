package nong.soon.bae.contorller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.service.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	@Autowired
	private ProductService service;
	
	// ���� ����(���̺�) �����
	@RequestMapping("createProduct")
	public String createProduct(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("username", username);
		return "product/createProduct";
	}
	
	// ���� ���� (���̺�) �����222
	@RequestMapping("createProductPro")
	public String createProductPro(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("username", username);
		service.createProduct(username);
		service.createSEQ(username);
		return "redirect:/product/productMain";
	}
	
	// �ϴ� ���� ����
	@RequestMapping("productMain")
	public String productMain(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("username", username);
		return "product/productMain";
	}
	
	// FINISH
	
	// ��ǰ ���
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
	public String sample() {
		return "product/sample";
	}
	
	
}
