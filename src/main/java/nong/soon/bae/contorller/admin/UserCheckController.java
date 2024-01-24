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
	
	@RequestMapping("/searchResult") //검색결과
	public String searchResult(Model model,@RequestParam(value="pageNum", defaultValue="1") int pageNum,String userSearch) {
		service.findUser(model,pageNum,userSearch);
		return "admin/usercheck/searchResult";
	}
	
	@RequestMapping("/userall") //일반 유저 리스트 
	public String userall(Model model,@RequestParam(value="pageNum", defaultValue="1") int pageNum) {
		service.userlist(pageNum, model);
		return "admin/usercheck/userall";
	}
	
	@RequestMapping("/stopPro") //회원 정지하기 
	public String stopPro(@RequestParam("username") String username, 
            				@RequestParam("reason") String reason) {
		service.userstop(username);
		service.blacklistInsert(username,reason);
		return "redirect:/admin/usercheck";
	}
	
	@RequestMapping("/blacklist")	//정지회원목록
	public String blacklist(Model model,@RequestParam(value="pageNum", defaultValue="1") int pageNum) {
		service.blacklist(pageNum,model);
		return "admin/usercheck/blacklist";
	}
	
	@RequestMapping("/blacksearchResult")	//정지회원검색
	public String blacksearchResult(Model model,@RequestParam(value="pageNum", defaultValue="1") int pageNum,String userSearch) {
		service.blackFindUser(model,pageNum,userSearch);
		return "admin/usercheck/blacksearchResult";
	}
	
	@RequestMapping("/reuserPro") //정지회원복구하기
	public String reuserPro(@RequestParam("username") String username) {
		service.reuser(username);
		service.deleteblacklist(username);
		return "redirect:/admin/blacklist";
	}
	
	@RequestMapping("addcategory") //게시판 물품 카테고리 추가하기 
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
	
	
	@RequestMapping("addSubCate") //게시판 물품 카테고리 추가하기 
	public String addSubCate(Model model,@RequestParam(value="cate1Select",required = false ) String cate1Select ) {
		if(cate1Select != null) {
			service.showSubCate(model,Integer.parseInt(cate1Select));
			model.addAttribute("cate1Select",cate1Select);
			int subMaxNum = service.subMaxNum(Integer.parseInt(cate1Select));
			model.addAttribute("subMaxNum",subMaxNum);
		
		}
		return "admin/usercheck/addSubCate";
	}
	
	
	@RequestMapping("addCatePro") //게시판 물품 카테고리 추가하기 
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
		                    file.transferTo(targetFile); // 파일 저장
		                 
		                    service.insertNewCate(num,addCate); //대분류 카테고리 추가하기 
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
	
	
	@RequestMapping("addDetailCate") //소분류 물품 추가하기 
	public String addDeailCate(Model model ) {
		service.showCate(model);
		return "admin/usercheck/addDetailCate";
		
	}
	
	@RequestMapping("addDetailCateForm") //소분류 물품 추가하기 
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
	
	

	@RequestMapping("addDetailCatePro") // 소분류 물품 추가하기
	public String addDetailCatePro(Model model, 
	                    @RequestParam(value="cate1Select", required = false) String cate1Select,
	                    @RequestParam(value="addDetail") String addDetail,
	                    @RequestParam(value="subMaxNum") String subMaxNum,
	                    @RequestParam(value="datailMaxNum") String datailMaxNum,
	                    @RequestParam("addImage") MultipartFile[] files,
	                    HttpServletRequest request) {

	    boolean fileFormatValid = true; 
	    boolean operationSuccess = false; 
	    // 세부 카테고리 정보 저장
	    if (addDetail != null && !addDetail.isEmpty()) {
	        service.insertDetailCate(Integer.parseInt(cate1Select), Integer.parseInt(subMaxNum), Integer.parseInt(datailMaxNum), addDetail);
	        operationSuccess = true; // 카테고리 추가 성공
	    }
	    // 파일 저장 로직
	    if (files != null && files.length > 0) {
	        for (MultipartFile file : files) {
	            if (!file.isEmpty()) {
	                String originalFileName = file.getOriginalFilename();
	                String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
	                if (ext.equals(".jpg") || ext.equals(".png") || ext.equals(".jpeg")) {
	                    String realname = "addCate_" + cate1Select + "_" + subMaxNum + "_" + datailMaxNum + ext;
	                    File targetFile = new File(request.getServletContext().getRealPath("/resources/img/") + realname);
	                    try {
	                        file.transferTo(targetFile); // 파일 저장
	                        service.addDetailFile(realname,addDetail);
	                        operationSuccess = true; // 파일 업로드 및 카테고리 추가 성공
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                        operationSuccess = false; // 오류 발생
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
	        model.addAttribute("operationSuccess", "작업이 성공적으로 완료되었습니다.");
	    }
	    
	    if (!fileFormatValid) {
	        model.addAttribute("fileFormatError", "파일 형식이 올바르지 않습니다. 지원되는 형식: .jpg, .png, .jpeg");
	        return "redirect:/admin/addcategory";
	    }

	    return "redirect:/admin/addcategory";
	}
	
	
	@RequestMapping("updateSubCate") //소분류 물품 추가하기 
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
			
	@RequestMapping("addSubCatePro") //게시판 물품 카테고리 추가하기 
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