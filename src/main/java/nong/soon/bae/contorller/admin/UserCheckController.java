package nong.soon.bae.contorller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nong.soon.bae.service.UserCheckService;

@Controller
@RequestMapping("/admin/*")
public class UserCheckController {

	@Autowired
	private UserCheckService service;
	
	@RequestMapping("/userall")
	public String userall(Model model,@RequestParam(value="pageNum", defaultValue="1") int pageNum) {
		
		service.userlist(pageNum, model);
		return "admin/usercheck/userall";
	}
	

}
