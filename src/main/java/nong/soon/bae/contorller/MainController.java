package nong.soon.bae.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.service.AreaService;
import nong.soon.bae.service.CategoryService;
import nong.soon.bae.service.MainService;

@Controller
@RequestMapping("/main/*")
public class MainController {
	@Autowired
	private MainService service;
	
	@Autowired
	private AreaService areaservice;

	@Autowired
	private CategoryService cateservice;
		
	@RequestMapping("main")
	public String main(Model model, String categoryNum, String cate1, String cate2, String cate3, String userSearch) {
		if (categoryNum==null) {
			categoryNum = "1";
		}
		service.seasonCategory(model, Integer.parseInt(categoryNum));
		if(cate1!=null && cate2!=null && cate3!=null ) {
			service.detailSeasonCategory(model, cate1, cate2, cate3);
		}
		return "main/main";
	}
	
	@RequestMapping("season")
	public String season(Model model, String categoryNum) {
		if (categoryNum==null) {
			categoryNum = "1";
		}
		service.seasonCategory(model, Integer.parseInt(categoryNum));
		return "main/season";
	}
	
	@RequestMapping("detail")
	public String seasonDetail(Model model, String cate1, String cate2, String cate3) {
		service.detailSeasonCategory(model, cate1, cate2, cate3);
		
		return "main/seasonDetail";
	}
	
	@RequestMapping("charts")
	public String chart(Model model, String cate1, String cate2, String cate3) {
		if(cate1!=null && cate2!=null && cate3!=null ) {
			service.showChart(model, cate1, cate2, cate3);
		}
		return "main/chart";
	}

	@RequestMapping("menu")
	public String main(Model model, String cate1, String cate2) {	
		List<ProductCategoryDTO> catelist = cateservice.cateMenu(model);
		model.addAttribute("catelist",catelist);
		if (cate1 == null && cate2 == null) { //전체항목리스트 
			cateservice.allproductlist(model); 
		} if (cate1 != null && cate2 == null) {
			cateservice.catelistdeatil(model, cate1);  //cate1 리스트
			cateservice.cateprodutlist (model, cate1); //cate1  상품리스트
		}if(cate1 != null && cate2 != null ){
			cateservice.catelistdeatil(model, cate1);
			cateservice.cateprodictlistdetail (model,cate1 ,cate2); // cate2 상품리스트 
		}
	
		
		return "main/categorymain";
	}
	
	@RequestMapping("menulist")
	public String main2(Model model, String cate1, String cate2) {
		
		return "main/categorylist";
	}
	
	@RequestMapping("menulistDetail")
	public String main3(Model model, String cate1,String cate2 ) {
		
		return "main/catelistDetail";
	}
	
	@RequestMapping("areamain")
	public String area(Model model, @RequestParam(value="areaNum", defaultValue="1") int areaNum,  
											String area1, String area2 ) {
		areaservice.arealist(areaNum,model);
		//List<AreaDTO> arealist = areaservice.areaMenu(model);
		//model.addAttribute("arealist",arealist);
		if (area1 == null && area2 == null) { //전체항목리스트 
			areaservice.allproductlist(model); 
		} 
		if (area1 != null && area2 == null) {
			//areaservice.arealistpage (areaNum,model,area1);
			areaservice.arealistdeatil(model, area1);  //area1 리스트
			areaservice.areaprodutlist (model, area1); //area1  상품리스트
		}
		if(area1 != null && area2 != null ){
			areaservice.arealistdeatil(model, area1);
			areaservice.areaprodictlistdetail (model, area1,area2); // area2 상품리스트 
		}
		return "main/areamain";
	}
	
	@RequestMapping("arealist")
	public String areafind(Model model, String area1, String area2) {
		if (area1 != null && area2 == null) {
			areaservice.arealistdeatil(model, area1);  //area1 리스트
			areaservice.areaprodutlist (model, area1); //area1  상품리스트
		}
		return "main/arealist";
	}
	
	@RequestMapping("arearesult")
	public String arearesult(Model model , String area1,String area2) {
		if(area1 != null && area2 != null ){
			areaservice.areaprodictlistdetail (model, area1,area2); // area2 상품리스트 
		}
		return "main/arearesult";
	}
	
	@RequestMapping("chart")
	public String categoryChart(Model model, String cate1, String cate2, String cate3, String categoryNum) {
		if(categoryNum == null) {
			categoryNum = "1";
		}
		if(cate1 == null && cate2 == null && cate3 == null) {
			cate1 = "1";
			cate2 = "1";
			cate3 = "1";
		}
		service.cateMenu(model);
		service.showCategory(model, cate1, cate2, cate3, Integer.parseInt(categoryNum));
		service.showChart(model, cate1, cate2, cate3);
		return "main/categoryChart";
	}
	
	@RequestMapping("search")
	public String search() {
		return "/main/search";
	}
	
	@RequestMapping("result")
	public String result(Model model, String userSearch, String searchNum) {
		if(searchNum == null) {
			searchNum = "1";
		}
		service.findProduct(model, userSearch, Integer.parseInt(searchNum));
		return "/main/result";
	}
}
