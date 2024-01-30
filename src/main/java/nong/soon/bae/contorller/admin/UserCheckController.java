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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ShopListDTO;
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
	
	
	@RequestMapping("/stopPro") //ȸ�� �����ϱ� 
	public String stopPro(@RequestParam("username") String username, 
	                      @RequestParam("reason") String reason,
	                      RedirectAttributes redirectAttributes) {
	    boolean success = true;

	    try {
	        service.userstop(username);
	        service.blacklistInsert(username, reason);
	        
	        List<ShopListDTO> findUser = service.findShop(username);
	        //���� ��� ȸ���� ������ �ִ� ��� ���� ���� �����ϱ� 
	        if (findUser != null) {
	            service.shopstop(username); 
	            service.allstop(username);
	        }
	    } catch (Exception e) {
	        success = false;
	        
	    }
	    if (success) {
	        redirectAttributes.addFlashAttribute("stopstatus", 1); // ���� �޽��� ����
	        
	    } else {
	        redirectAttributes.addFlashAttribute("stopstatus", 0); // ���� �޽��� ����
	    }

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
	public String reuserPro(@RequestParam("username") String username,
	                        RedirectAttributes redirectAttributes) {
	    boolean resuccess = false;

	    service.reuser(username);
	    service.deleteblacklist(username);
	    // ���� ȸ�� ���� ����
	    List<ShopListDTO> refindUser = service.findShop(username);
	    if(refindUser != null) {
	        service.reshop(username);
	        service.reall(username);
	    }
	    resuccess = true;
	    if (resuccess) {
	        redirectAttributes.addFlashAttribute("restatus", 1); // ���� ���� �޽��� ����
	    } else {
	        redirectAttributes.addFlashAttribute("restatus", 0); // ���� ���� �޽��� ����
	    }

	    return "redirect:/admin/blacklist";
	}

	
	@RequestMapping("addcategory") //�Խ��� ��ǰ ī�װ� ���������� 
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
		
		return "admin/usercheck/addcategory";
	}
	
	@RequestMapping("addCatePro") //��з� ī�װ� �߰��ϱ�
	public String addCatePro(Model model, @RequestParam(value="num") int num,
	                         @RequestParam("addCate") String addCate, 
	                         @RequestParam("categoryImage") MultipartFile[] files,
	                         HttpServletRequest request,RedirectAttributes redirectAttributes) {

	    String realRoot = request.getServletContext().getRealPath("/resources/img/");
	    boolean operationSuccess = false;
	    boolean fileFormatValid = true;
	    int cateNum = num + 1;

	    if (addCate != null && !addCate.isEmpty()) {
	        service.insertNewCate(cateNum, addCate);
	        operationSuccess = true;
	    }

	    if (files != null && files.length > 0) {
	        for (MultipartFile file : files) {
	            if (!file.isEmpty()) {
	                try {
	                    String originalFileName = file.getOriginalFilename();
	                    String ext = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();
	                    if (ext.equals(".jpg") || ext.equals(".png") || ext.equals(".jpeg")) {
	                        String realname = "addCate_" + cateNum + "_0_0" + ext;
	                        File targetFile = new File(realRoot + realname);
	                        file.transferTo(targetFile); // ���� ���� 
	                        service.addCateFile(addCate, realname);
	                        operationSuccess = true;
	                    } else {
	                        fileFormatValid = false;
	                        break;
	                    }
	                } catch (IOException e) {
	                    e.printStackTrace();
	                    operationSuccess = false;
	                    break;
	                }
	            }
	        }
	    }
	    if (!fileFormatValid && operationSuccess) {
	        // ���� Ȯ���� �˻� ������ �̹� �߰��� ī�װ� ���� ����
	    	service.deleteCate(cateNum, addCate);
	        operationSuccess = false;
	        redirectAttributes.addFlashAttribute("status", 0);  //�߰� ���� �޽��� ���� 
	    }else {
	        redirectAttributes.addFlashAttribute("status", 1); // �߰� �Ϸ� �޽��� ���� 
	    }
	    
	    return "redirect:/admin/addcategory";
	}

	
	
	@RequestMapping("addDetailCate") //�Һз� ��ǰ ���
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
	                    HttpServletRequest request,RedirectAttributes redirectAttributes) {

	    boolean fileFormatValid = true; 
	    boolean operationSuccess = false; 
	    int detailNum = Integer.parseInt(datailMaxNum) + 1;
	    String realRoot = request.getServletContext().getRealPath("/resources/img/");

	    // ���� ī�װ� ���� ����
	    if (addDetail != null && !addDetail.isEmpty()) {
	        service.insertDetailCate(Integer.parseInt(cate1Select), Integer.parseInt(subMaxNum), detailNum, addDetail);
	        operationSuccess = true; // ī�װ� �߰� ����
	    }
	    // ���� ���� ����
	    if (files != null && files.length > 0) {
	        for (MultipartFile file : files) {
	            if (!file.isEmpty()) {
	                try {
	                    String originalFileName = file.getOriginalFilename();
	                    String ext = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();
	                    if (ext.equals(".jpg") || ext.equals(".png") || ext.equals(".jpeg")) {
	                        String realname = "addDetailCate_" + cate1Select + "_" + subMaxNum + "_" + datailMaxNum + ext;
	                        File targetFile = new File(realRoot + realname);
	                        file.transferTo(targetFile); // ���� ����
	                        service.addDetailFile(realname, addDetail);
	                        operationSuccess = true; // ���� ���ε� �� ī�װ� �߰� ����
	                    } else {
	                        fileFormatValid = false;
	                        break;
	                    }
	                } catch (IOException e) {
	                    e.printStackTrace();
	                    operationSuccess = false;
	                    break;
	                }
	            }
	        }
	    }
	    if (!fileFormatValid && operationSuccess) {
	        // �̹� �߰��� ī�װ� ���� ����
	        service.deleteDetailCate(detailNum, addDetail);
	        operationSuccess = false;
	        redirectAttributes.addFlashAttribute("status", 0); //�߰� ���� �޽��� ����
	    }else {
	        redirectAttributes.addFlashAttribute("status", 1); // �߰��� �Ϸ� �޽��� ����
	    }
	    return "redirect:/admin/addcategory";
	}

	
	@RequestMapping("updateSubCate") //�Һз� ��ǰ �߰��ϱ� 
	public String updateSubCate(Model model ) {
		service.showCate(model);
		return "admin/usercheck/updateSubCate";
		
	}
	
	@RequestMapping("updateSubCateForm") //�ߺз� �׸� ���Ӱ� �����
	public String updateSubCateForm(Model model, 
						@RequestParam(value="cate1Select",required = false ) String cate1Select ) {	
		int subMaxNum= service.subMaxNum(Integer.parseInt(cate1Select)); 
		if(cate1Select != null) {
			service.showEtcCate(model,Integer.parseInt(cate1Select),subMaxNum);
		}
		
		model.addAttribute("subMaxNum",subMaxNum);
		model.addAttribute("cate1Select",cate1Select);
		return "admin/usercheck/updateSubCateForm";
	}
	
	
	@RequestMapping("updateSubCatePro") //��Ÿ�׸� ������ ������Ʈ
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
}