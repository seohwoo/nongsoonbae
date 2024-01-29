package nong.soon.bae.contorller.membership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nong.soon.bae.service.MembershipService;

@Controller
@RequestMapping("/membership/*")
public class MembershipController {
	
	@Autowired
	private MembershipService service;

	@RequestMapping("detailChart")
	public String detailChart(Model model, String year,String month,String value,String name) {
		
		service.findDetailChart(model, year, month, name, value);
		
		
		
		return "/membership/chart/detailChart";
	}
	
}
