package nong.soon.bae.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.service.AreaService;

@Controller
@RequestMapping("/area/*")
public class AreaController {

	@Autowired
	private AreaService service;
	
	@RequestMapping("main")
	public String main(Model model, String areaname) {
		List<AreaDTO> dto = service.catelist(areaname);
		model.addAttribute("dto",dto);
		return "area/areamain";
	}
	
	@RequestMapping("category")
	public String main2(Model model, int area1) {
		List<AreaDTO> areas = service.catelistarea(area1);
		model.addAttribute("areas",areas);
		return "area/areacategory";
	}
	
}
