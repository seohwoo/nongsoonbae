package nong.soon.bae.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping("chart")
	public String chart(Model model, String cate1, String cate2, String cate3) {
		if(cate1!=null && cate2!=null && cate3!=null ) {
			service.showChart(model, cate1, cate2, cate3);
		}
		return "main/chart";
	}
	
	@RequestMapping("menu")
	public String main(Model model, String catename) {
		List<ProductCategoryDTO> dto = cateservice.catemenu(catename);
		model.addAttribute("dto",dto);
		return "main/categorymain";
	}
	
	@RequestMapping("menulist")
	public String main2(Model model, int cate1) {
		List<ProductCategoryDTO> menu = cateservice.catelist(cate1);
		model.addAttribute("menu",menu);
		return "main/categorylist";
	}
	
	
	@RequestMapping("menulistDetail")
	public String main3(Model model, String cate1,String cate2 ) {
		cateservice.cateDetail(model, cate1, cate2);
		return "main/catelistDetail";
	}
	
	@RequestMapping("arealist")
	public String area(Model model, String areaname) {
		List<AreaDTO> dto = areaservice.catelist(areaname);
		model.addAttribute("dto",dto);
		return "main/areamain";
	}
	
	@RequestMapping("areas")
	public String area2(Model model, int area1) {
		List<AreaDTO> areas = areaservice.catelistarea(area1);
		model.addAttribute("areas",areas);
		return "main/areacategory";
	}
	
	
	
	
	
}
