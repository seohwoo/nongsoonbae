package nong.soon.bae.contorller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import nong.soon.bae.bean.ProductCategoryDTO;
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
	public String addcategory(Model model,@RequestParam(value="cate1Select",required = false ) String cate1Select, String isImg ) {
		if(isImg==null) {
			isImg = "2";
		}
		service.showCate(model);
		int maxNum = service.maxNum();
		model.addAttribute("num", maxNum);
		if(cate1Select != null) {
		service.showSubCate(model,Integer.parseInt(cate1Select));
		}
		model.addAttribute("isImg", isImg);
		return "admin/usercheck/addcategory";
	}
	
	
	@RequestMapping("addSubCate") //�Խ��� ��ǰ ī�װ� �߰��ϱ� 
	public String addSubCate(Model model,@RequestParam(value="cate1Select",required = false ) String cate1Select ) {
		if(cate1Select != null) {
			service.showSubCate(model,Integer.parseInt(cate1Select));
			model.addAttribute("cate1Select",cate1Select);
			int subMaxNum = service.subMaxNum(Integer.parseInt(cate1Select));
			model.addAttribute("subMaxNum",subMaxNum);
		
		}
		return "admin/usercheck/addSubCate";
	}
	
	
	@RequestMapping("addCatePro") //�Խ��� ��ǰ ī�װ� �߰��ϱ� 
	public String addCatePro(Model model,@RequestParam(value="num") int num,
									@RequestParam("addCate")String addCate, 
									@RequestParam("categoryImage") MultipartFile[] files,HttpServletRequest request) {
	    String realRoot = request.getServletContext().getRealPath("/resources/img/");
	    String isImg = "0";
	    if (files != null && files.length > 0) {
	       
	        for (MultipartFile file : files) {
	            if (!file.isEmpty()) {
	                try {
	                    String originalFileName = file.getOriginalFilename();
	                    String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
	                    if(ext.equals(".jpg") || ext.equals(".png") || ext.equals(".jpeg")) {
	                    	String realname = "addCate_" + num + "_"+ "0" + "0" + ext ;
		                    File targetFile = new File(realRoot + realname);
		                    file.transferTo(targetFile); // ���� ����
		                 
		                    service.insertNewCate(num,addCate); //��з� ī�װ� �߰��ϱ� 
		                    service.addCateFile(addCate,realname);
		                    isImg = "1";
	                    }
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	  	    return "redirect:/admin/addcategory?isImg=" + isImg;
	}
	
	
	@RequestMapping("addDetailCate") //�Һз� ��ǰ �߰��ϱ� 
	public String addDeailCate(Model model ) {
		service.showCate(model);
		return "admin/usercheck/addDetailCate";
		
	}
	
	@RequestMapping("addDetailCateForm") //�Һз� ��ǰ �߰��ϱ� 
	public String addDetailCate(Model model, 
						@RequestParam(value="cate1Select",required = false ) String cate1Select ) {
		if(cate1Select != null) {
			service.showDetailCate(model,Integer.parseInt(cate1Select));
		}
		int subMaxNum= service.subMaxNum(Integer.parseInt(cate1Select)); 
		int datailMaxNum = service.subDetailMaxNum(subMaxNum,Integer.parseInt(cate1Select));
		
		model.addAttribute("subMaxNum",subMaxNum);
		model.addAttribute("datailMaxNum",datailMaxNum);
		model.addAttribute("cate1Select",cate1Select);
		return "admin/usercheck/addDetailCateForm";
		
	}
	
	

	@RequestMapping("addDetailCatePro") // �Һз� ��ǰ �߰��ϱ�
	public String addDetailCatePro(Model model, 
	                    @RequestParam(value="cate1Select", required = false) String cate1Select,
	                    @RequestParam(value="addDetail") String addDetail,
	                    @RequestParam(value="subMaxNum") String subMaxNum,
	                    @RequestParam(value="datailMaxNum") String datailMaxNum,
	                    @RequestParam("addImage") MultipartFile[] files,
	                    HttpServletRequest request) {

	    boolean fileFormatValid = true; 
	    boolean operationSuccess = false; 
	    // ���� ī�װ� ���� ����
	    if (addDetail != null && !addDetail.isEmpty()) {
	        service.insertDetailCate(Integer.parseInt(cate1Select), Integer.parseInt(subMaxNum), Integer.parseInt(datailMaxNum), addDetail);
	        operationSuccess = true; // ī�װ� �߰� ����
	    }
	    // ���� ���� ����
	    if (files != null && files.length > 0) {
	        for (MultipartFile file : files) {
	            if (!file.isEmpty()) {
	                String originalFileName = file.getOriginalFilename();
	                String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
	                if (ext.equals(".jpg") || ext.equals(".png") || ext.equals(".jpeg")) {
	                    String realname = "addCate_" + cate1Select + "_" + subMaxNum + "_" + datailMaxNum + ext;
	                    File targetFile = new File(request.getServletContext().getRealPath("/resources/img/") + realname);
	                    try {
	                        file.transferTo(targetFile); // ���� ����
	                        service.addDetailFile(realname,addDetail);
	                        operationSuccess = true; // ���� ���ε� �� ī�װ� �߰� ����
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                        operationSuccess = false; // ���� �߻�
	                        break;
	                    }
	                } else {
	                    fileFormatValid = false;
	                    break;
	                }
	            }
	        }
	    }
	    if (operationSuccess) {
	        model.addAttribute("operationSuccess", "�۾��� ���������� �Ϸ�Ǿ����ϴ�.");
	    }
	    
	    if (!fileFormatValid) {
	        model.addAttribute("fileFormatError", "���� ������ �ùٸ��� �ʽ��ϴ�. �����Ǵ� ����: .jpg, .png, .jpeg");
	        return "redirect:/admin/addcategory";
	    }

	    return "redirect:/admin/addcategory";
	}
	
	
	@RequestMapping("updateSubCate") //�Һз� ��ǰ �߰��ϱ� 
	public String updateSubCate(Model model ) {
		service.showCate(model);
		return "admin/usercheck/updateSubCate";
		
	}
	
	@RequestMapping("updateSubCateForm") 
	public String updateSubCateForm(Model model, 
						@RequestParam(value="cate1Select",required = false ) String cate1Select ) {	
		int subMaxNum= service.subMaxNum(Integer.parseInt(cate1Select)); 
		String etcName = service.findEtcName(Integer.parseInt(cate1Select),subMaxNum);
		if(cate1Select != null) {
			service.showEtcCate(model,Integer.parseInt(cate1Select),subMaxNum);
		}
		
		model.addAttribute("subMaxNum",subMaxNum);
		System.out.println(subMaxNum);
		model.addAttribute("cate1Select",cate1Select);
		return "admin/usercheck/updateSubCateForm";
	}
	
	
	@RequestMapping("updateSubCatePro") 
	public String updateSubCatePro(Model model,
			@RequestParam(value="cate1Select",required = false ) String cate1Select,
			@RequestParam(value="subMaxNum",required = false ) String subMaxNum,
			@RequestParam(value="newCateName",required = false) String newCateName) {
		
		String etcName = service.findEtcName(Integer.parseInt(cate1Select),Integer.parseInt(subMaxNum));
		if(newCateName != null) {
			service.updateCateName(newCateName,Integer.parseInt(cate1Select),Integer.parseInt(subMaxNum));
			int etcNum = Integer.parseInt(subMaxNum) + 1;
			service.updateEtcCate(Integer.parseInt(cate1Select),etcNum,etcName);
		}
		
		
		return "redirect:/admin/addcategory";
	}
			
	@RequestMapping("addSubCatePro") //�Խ��� ��ǰ ī�װ� �߰��ϱ� 
	public String addSubCatePro(Model model,
					@RequestParam(value="cate1Select",required = false ) String cate1Select,
					@RequestParam(value="subMaxNum") int subMaxNum,
					@RequestParam("addSubCate")String addSubCate,
					@RequestParam("addCate1")String addCate1,
					@RequestParam("addCateNum1")String addCateNum1,
					@RequestParam("addCate2")String addCate2,
					@RequestParam("addCateNum2")String addCateNum2) {
		if(addSubCate != null && addCate1 !=null ) {
			service.insertSubCate(Integer.parseInt(cate1Select),subMaxNum,addSubCate);
			service.insertSubDetailCate(Integer.parseInt(cate1Select),subMaxNum,Integer.parseInt(addCateNum1),addCate1);
		}
		if(addCate2 !=null) {
			service.insertSubDetailCate(Integer.parseInt(cate1Select),subMaxNum,Integer.parseInt(addCateNum2),addCate2);
		}	
		return "redirect:/admin/addcategory";
	}
	
}