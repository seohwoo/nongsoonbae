package nong.soon.bae.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nong.soon.bae.service.MainService;

@Controller
@RequestMapping("/main/*")
public class MainController {

	
	@Autowired
	private MainService service;
	
	@RequestMapping("main")
	public String main(Model model, String categoryNum) {
		if (categoryNum==null) {
			categoryNum = "1";
		}
		service.seasonCategory(model, Integer.parseInt(categoryNum));
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
	
	
}
