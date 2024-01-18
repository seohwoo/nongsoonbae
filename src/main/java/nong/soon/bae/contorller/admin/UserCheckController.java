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
	
	@RequestMapping("/userall") //�Ϲ� ���� ����Ʈ 
	public String userall(Model model,@RequestParam(value="pageNum", defaultValue="1") int pageNum) {
		service.userlist(pageNum, model);
		return "admin/usercheck/userall";
	}
	
	@RequestMapping("/stopPro") //ȸ�� �����ϱ� 
	public String stopPro(@RequestParam("username") String username, 
            				@RequestParam("reason") String reason) {
		service.userstop(username);
		service.blacklistInsert(username,reason);
		return "redirect:/admin/userall";
	}
	
	@RequestMapping("/blacklist")	//����ȸ�����
	public String blacklist(Model model,@RequestParam(value="pageNum", defaultValue="1") int pageNum) {
		service.blacklist(pageNum,model);
		return "admin/usercheck/blacklist";
	}
	
	@RequestMapping("/reuserPro") //����ȸ�������ϱ�
	public String reuserPro(@RequestParam("username") String username) {
		service.reuser(username);
		service.deleteblacklist(username);
		return "redirect:/admin/blacklist";
	}
}
