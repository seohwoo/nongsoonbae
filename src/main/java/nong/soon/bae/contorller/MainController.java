package nong.soon.bae.contorller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nong.soon.bae.bean.AllProductDTO;
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
	public String main(Model model, String categoryNum, String cate1, String cate2, String cate3) {
		if (categoryNum==null) {
			categoryNum = "1";
		}
		service.seasonCategory(model, Integer.parseInt(categoryNum));
		if(cate1!=null && cate2!=null && cate3!=null ) {
			service.showChart(model, cate1, cate2, cate3);
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
	public String main(Model model, String cate1, String cate2, String cate3) {
		List<ProductCategoryDTO> dto = cateservice.catemenu();
		model.addAttribute("dto",dto);
		if(cate1 == null && cate2 == null && cate3 == null  ) {
			cate1 = "0";
			cate2 = "0";
			cate3 = "0";
			cateservice.cateproduct();
		}
		    cateservice.catelist(model, cate1, cate2, cate3);
		    cateservice.cateDetail(model, cate1, cate2);
		/*if(cate1 !=null && cate2 != null && cate3 != null ) {
			cateservice.catelist(model, cate1, cate2, cate3);
			cateservice.cateDetail(model, cate1, cate2);
		}*/
		return "main/categorymain";
	}
	
	@RequestMapping("menulist")
	public String main2(Model model, String cate1, String cate2, String cate3) {
		if(cate1 !=null && cate2 == "0" ) {
		cateservice.catelist(model, cate1, cate2, cate3);
		} 
		return "main/categorylist";
	}
	
	
	@RequestMapping("menulistDetail")
	public String main3(Model model, String cate1,String cate2 ) {
		cateservice.cateDetail(model, cate1, cate2);
		return "main/catelistDetail";
	}
	
	@RequestMapping("areamain")
	public String area(Model model, String area1, String area2) {
		List<AreaDTO> dto = areaservice.arealist();
		model.addAttribute("dto",dto);
		if (area1 == null) area1 = "0";
		if (area1 == null) area2 = "0";
			areaservice.areaDetail(model, area1, area2);	
		/*if(selectedValue !=null) {
			areaservice.arealistdetail(model, selectedValue);
			areaservice.findareaname(model, selectedValue);
		}*/
		return "main/areamain";
	}
	
	@RequestMapping("areafind")
	public String areafind(Model model, String area1, String area2, String areaname) {
		//List<AreaDTO> dto = areaservice.catelist(areaname);
		//model.addAttribute("dto",dto);
		List<AreaDTO> list = areaservice.arealist();
		model.addAttribute("list",list);
		return "main/areafind";
	}
	
	
	@RequestMapping("arearesult")
	public String arearesult(Model model,String area1) {
		if(area1 !=null) {
			areaservice.arealistdetail(model, area1);
		}
		return "main/arearesult";
	}
	
	@RequestMapping("test")
	public String test() {
		return "main/test";
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
}
