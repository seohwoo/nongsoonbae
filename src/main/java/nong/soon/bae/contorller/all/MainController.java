package nong.soon.bae.contorller.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.data.ApiKeys;
import nong.soon.bae.service.AreaService;
import nong.soon.bae.service.CategoryService;
import nong.soon.bae.service.MainService;

@RequestMapping("/nsb/*")
@Controller
public class MainController implements ApiKeys{
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
		model.addAttribute("pluginKey", pluginKey);
		return "all/main/main";
	}
	
	@RequestMapping("result")
	public String result(Model model, String userSearch, String searchNum) {
		if(searchNum == null) {
			searchNum = "1";
		}
		service.findProduct(model, userSearch, Integer.parseInt(searchNum));
		return "all/main/result";
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
		return "all/main/categoryChart";
	}
	
	@RequestMapping("menu")
	public String main(Model model, String cate1, String cate2) {	
		List<ProductCategoryDTO> catelist = cateservice.cateMenu(model);
		model.addAttribute("catelist",catelist);
		if (cate1 == null && cate2 == null) { //��ü�׸񸮽�Ʈ 
			cateservice.allproductlist(model); 
		} if (cate1 != null && cate2 == null) {
			cateservice.catelistdeatil(model, cate1);  //cate1 ����Ʈ
			cateservice.cateprodutlist (model, cate1); //cate1  ��ǰ����Ʈ
		}if(cate1 != null && cate2 != null ){
			cateservice.catelistdeatil(model, cate1);
			cateservice.cateprodictlistdetail (model,cate1 ,cate2); // cate2 ��ǰ����Ʈ 
		}
		if(cate1==null) {
			cate1 = "0";
			model.addAttribute("cate1", Integer.parseInt(cate1));
		}
		return "all/main/categorymain";
	}
	
	@RequestMapping("area")
	public String area(Model model, @RequestParam(value="areaNum", defaultValue="1") int areaNum,  
											String area1, String area2 ) {
		areaservice.arealist(areaNum,model);
		//List<AreaDTO> arealist = areaservice.areaMenu(model);
		//model.addAttribute("arealist",arealist);
		if (area1 == null && area2 == null) { //��ü�׸񸮽�Ʈ 
			areaservice.allproductlist(model); 
		} 
		if (area1 != null && area2 == null) {
			//areaservice.arealistpage (areaNum,model,area1);
			areaservice.arealistdeatil(model, area1);  //area1 ����Ʈ
			areaservice.areaprodutlist (model, area1); //area1  ��ǰ����Ʈ
		}
		if(area1 != null && area2 != null ){
			areaservice.arealistdeatil(model, area1);
			areaservice.areaprodictlistdetail (model, area1,area2); // area2 ��ǰ����Ʈ 
		}
		if(area1==null) {
			area1 = "0";
			model.addAttribute("area1", Integer.parseInt(area1));
		}
		return "all/main/areamain";
	}
	
}
