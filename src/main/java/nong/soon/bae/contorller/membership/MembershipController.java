package nong.soon.bae.contorller.membership;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nong.soon.bae.service.MainService;
import nong.soon.bae.service.MembershipService;

@Controller
@RequestMapping("/membership/*")
public class MembershipController {
	
	
	
	@Autowired
	private MembershipService service;
	@Autowired
	private MainService mainService;

	@RequestMapping("detailChart")
	public String detailChart(Model model, String year,String month,String value,String name) {
		service.findDetailChart(model, year, month, name, value);
		return "/membership/chart/detailChart";
	}
	
	@RequestMapping("userChart")
	public String userChart(Model model, String cate1, String cate2, String cate3, String categoryNum) {
		if(categoryNum == null) {
			categoryNum = "1";
		}
		if(cate1 == null && cate2 == null && cate3 == null) {
			cate1 = "1";
			cate2 = "1";
			cate3 = "1";
		}
		mainService.cateMenu(model);
		mainService.showCategory(model, cate1, cate2, cate3, Integer.parseInt(categoryNum));
		return "/membership/chart/userPriceChart";
	}
	
	@RequestMapping("chart")
	public String categoryChart(Model model, String cate1, String cate2, String cate3, String categoryNum, Principal pri) {
		boolean isMembership = false;
		if(categoryNum == null) {
			categoryNum = "1";
		}
		if(cate1 == null && cate2 == null && cate3 == null) {
			cate1 = "1";
			cate2 = "1";
			cate3 = "1";
		}
		if(pri != null) {
			isMembership = mainService.isMembership(isMembership, pri.getName());
		}
		mainService.cateMenu(model);
		mainService.showCategory(model, cate1, cate2, cate3, Integer.parseInt(categoryNum));
		//service.showChart(model, cate1, cate2, cate3);
		model.addAttribute("isMembership", isMembership);
		return "all/main/categoryChart";
	}
	
}
