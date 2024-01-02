package nong.soon.bae.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.service.CategoryService;

@Controller
@RequestMapping("/cate/*")
public class CategoryController {
	
	@Autowired 
	private CategoryService service;
	
	@RequestMapping("menu")
	public String main(Model model, String catename) {
		List<ProductCategoryDTO> dto = service.catemenu(catename);
		model.addAttribute("dto",dto);
		return "category/categorymain";
	}
	
	@RequestMapping("menulist")
	public String main2(Model model, int cate1) {
		List<ProductCategoryDTO> menu = service.catelist(cate1);
		model.addAttribute("menu",menu);
		return "category/categorylist";
	}
	

}
