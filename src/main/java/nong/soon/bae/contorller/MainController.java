package nong.soon.bae.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import nong.soon.bae.service.MainService;

@Controller
@RequestMapping("/main/*")
public class MainController {

	
	@Autowired
	private MainService service;
	
	
	
	
}
