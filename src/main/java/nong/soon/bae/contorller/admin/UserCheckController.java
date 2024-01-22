package nong.soon.bae.contorller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.Locale.Category;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.service.UserCheckService;

@Controller
@RequestMapping("/admin/*")
public class UserCheckController {

	@Autowired
	private UserCheckService service;
		
	@RequestMapping("/usercheck") 
	public String usercheck(Model model,@RequestParam(value="pageNum", defaultValue="1") int pageNum,String userSearch) {
		service.userlist(pageNum, model);
		service.findUser(model,pageNum,userSearch);
		return "admin/usercheck/usercheck";
	}
	
	@RequestMapping("/searchResult") //�˻����
	public String searchResult(Model model,@RequestParam(value="pageNum", defaultValue="1") int pageNum,String userSearch) {
		service.findUser(model,pageNum,userSearch);
		return "admin/usercheck/searchResult";
	}
	
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
		return "redirect:/admin/usercheck";
	}
	
	@RequestMapping("/blacklist")	//����ȸ�����
	public String blacklist(Model model,@RequestParam(value="pageNum", defaultValue="1") int pageNum) {
		service.blacklist(pageNum,model);
		return "admin/usercheck/blacklist";
	}
	
	@RequestMapping("/blacksearchResult")	//����ȸ���˻�
	public String blacksearchResult(Model model,@RequestParam(value="pageNum", defaultValue="1") int pageNum,String userSearch) {
		service.blackFindUser(model,pageNum,userSearch);
		return "admin/usercheck/blacksearchResult";
	}
	
	@RequestMapping("/reuserPro") //����ȸ�������ϱ�
	public String reuserPro(@RequestParam("username") String username) {
		service.reuser(username);
		service.deleteblacklist(username);
		return "redirect:/admin/blacklist";
	}
	
	@RequestMapping("addcategory") //�Խ��� ��ǰ ī�װ� �߰��ϱ� 
	public String addcategory(Model model,@RequestParam(value="cate1Select",required = false ) String cate1Select ) {
		service.showCate(model);
		int maxNum = service.maxNum();
		model.addAttribute("num", maxNum);
		System.out.println(maxNum);
		if(cate1Select != null) {
		service.showSubCate(model,Integer.parseInt(cate1Select));
		}
		return "admin/usercheck/addcategory";
	}
	
	
	@RequestMapping("addSubCate") //�Խ��� ��ǰ ī�װ� �߰��ϱ� 
	public String addSubCate(Model model,@RequestParam(value="cate1Select",required = false ) String cate1Select ) {
		if(cate1Select != null) {
		service.showSelectCate1 (model,Integer.parseInt(cate1Select));
		service.showSubCate(model,Integer.parseInt(cate1Select));
		}
		return "admin/usercheck/addSubCate";
	}
	
	
	@RequestMapping("addCatePro") //�Խ��� ��ǰ ī�װ� �߰��ϱ� 
	public String addCatePro(Model model,@RequestParam(value="num") int num,
									@RequestParam("addCate")String addCate, 
									@RequestParam("categoryImage") MultipartFile[] files,HttpServletRequest request) {
		service.insertNewCate(num,addCate); //��з� ī�װ� �߰��ϱ� 
		String fileRoot = request.getServletContext().getRealPath("/resources/summernoteImage/");
	    String realRoot = request.getServletContext().getRealPath("/resources/realImage/");
	    if (files != null && files.length > 0) {
	        int cnt = 1;
	        for (MultipartFile file : files) {
	            if (!file.isEmpty()) {
	                try {
	                    String originalFileName = file.getOriginalFilename();
	                    String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
	                    String realname = "addCate_" + num + "_" + cnt ;
	                    File targetFile = new File(realRoot + realname);
	                    file.transferTo(targetFile); // ���� ����
	                    cnt++;
	                    service.addCateFile(addCate,realname);
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	  	    return "redirect:/admin/addcategory";
	}
}