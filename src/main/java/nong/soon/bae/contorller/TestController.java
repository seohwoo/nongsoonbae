package nong.soon.bae.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nong.soon.bae.service.TestService;


@Controller
@RequestMapping("/test/*")
public class TestController {
 
	@Autowired
	private TestService service;
	
	@RequestMapping("main")
	public String test(Model model) {
		int count = service.count();
		model.addAttribute("count", count);
		return "/test/main";
	}
	
	@RequestMapping("hello")
	public String hello() {
		return "/test/hello";
	}
	
	@RequestMapping("map")
	public String map() {
		return "/test/mapExample";
	}
	
	@RequestMapping("pay")
	public String pay() {
		return "/test/kakaoPay";
	}
	
	@RequestMapping("chart")
	public String chart() {
		return "/test/chart";
	}
	
	@RequestMapping("address")
	public String address() {
		return "/test/addressTest";
	}
	
	@RequestMapping("addressmap")
	public String addressmap() {
		return "/test/addressMap";
	}
	
	@RequestMapping("editor")
	public String editor() {
		return "/test/editor";
	}
	
	@RequestMapping("editorPro")
	public String editorPro(String editordata, Model model) {
		model.addAttribute("editordata", editordata);
		return "/test/editorPro";
	}
	
}
