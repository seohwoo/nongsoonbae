package nong.soon.bae.contorller.admin;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import nong.soon.bae.bean.NoticeBoardDTO;
import nong.soon.bae.service.NoticeService;
@Controller
public class NoticeController  {
	
	private static final Logger log = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	private NoticeService service;
	@Autowired
	private ArrayList<String> srcValues;
	@Autowired
	private ArrayList<String> realFiles;
	
	
	
	@RequestMapping("/admin/noticeForm")
	public String notice(Model model) {
		int maxNum = service.maxNum();
		model.addAttribute("num", maxNum);
		return "admin/notice/noticeForm";
	}
	
	@RequestMapping("/admin/noticePro")
	public String noticePro(String content, String title,  Model model,String[] fileNames, HttpServletRequest request,
							@RequestParam(value="num") int num,RedirectAttributes redirectAttributes) {
		String fileRoot = request.getServletContext().getRealPath("/resources/summernoteImage/");
		String realRoot = request.getServletContext().getRealPath("/resources/realImage/");
		int cnt = 1;
		content = content.replace("src=\"/resources/summernoteImage/", "src=\"/resources/realImage/");
		int files = 0; // 첨부된 파일 개수를 저장할 변수
		if(fileNames != null) {
			files = fileNames.length; 
	        isFile(fileNames, content); 
	        for (String filename : realFiles) {
	            try {
	                File sourceFile = new File(fileRoot + filename);
	                File targetDirectory = new File(realRoot);
	                String ext = filename.substring(filename.lastIndexOf("."));
	                String realname = "notice_"+num+"_" + cnt + ext;
	                Files.copy(sourceFile.toPath(), targetDirectory.toPath().resolve(realname), StandardCopyOption.REPLACE_EXISTING);
	                cnt++;
	                content = content.replace(filename, realname);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
			for (String filename : fileNames) {
				File sourceFile = new File(fileRoot+filename);
				sourceFile.delete();
			}
	    }
		NoticeBoardDTO dto = new NoticeBoardDTO();
		dto.setTitle(title); // 제목 설정
		dto.setContent(content); // 내용 설정
		dto.setFiles(files); // 첨부 파일 개수 설정
		try {
		    service.writeInsert(title, content, files);
		    redirectAttributes.addFlashAttribute("status", 1);
		} catch (Exception e) {
		    e.printStackTrace();
		    // 필요한 경우 실패 상태를 설정
		    redirectAttributes.addFlashAttribute("status", 0);
		}
		return "redirect:/admin/noticeList";
	
		
	}

	 public void isFile(String[] filenames, String content) {
		 srcValues.clear();
		 realFiles.clear();
		 Pattern pattern = Pattern.compile("src\\s*=\\s*\"([^\"]+)\"");
	     Matcher matcher = pattern.matcher(content);
	     while (matcher.find()) {
	         srcValues.add(matcher.group(1));
	     }
		 for (int i = 0; i < srcValues.size(); i++) {
			int lastSlashIndex = srcValues.get(i).lastIndexOf('/');
			if (lastSlashIndex != -1 && lastSlashIndex < srcValues.get(i).length() - 1) {
				srcValues.set(i, srcValues.get(i).substring(lastSlashIndex + 1));
			}
		 }
		 if(filenames.length != srcValues.size()) {
			 for (int i = 0; i < srcValues.size(); i++) {
				for (String filename : filenames) {
					if(filename.equals(srcValues.get(i))) {
						realFiles.add(srcValues.get(i));
					}
				}
			}
		 }else {
			 realFiles = srcValues;
		 }
	 }
	 
	 @RequestMapping(value = "uploadSummernoteImageFile", produces = "application/json", consumes = "multipart/form-data")
		public ResponseEntity<JsonNode> uploadSummernoteImageFile(@RequestPart("file") MultipartFile multipartFile,
		      HttpServletRequest request) {
		   ObjectMapper objectMapper = new ObjectMapper();
		   JsonNode responseJson;
		   String fileRoot = request.getServletContext().getRealPath("/resources/summernoteImage/");
			
		   try {
		      String originalFileName = multipartFile.getOriginalFilename();
		      String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		      String savedFileName = UUID.randomUUID() + extension;
		      Path targetPath = Path.of(fileRoot, savedFileName);
		      Files.copy(multipartFile.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
		      String imageUrl = request.getContextPath() + "/resources/summernoteImage/" + savedFileName;
		      responseJson = objectMapper.createObjectNode()
		            .put("url", imageUrl)
		            .put("responseCode", "success")
		            .put("fileName", savedFileName);
		      return ResponseEntity.ok(responseJson);
		   } catch (IOException e) {
		      responseJson = objectMapper.createObjectNode().put("responseCode", "error");
		      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseJson);
		   }
		}
	 
	 @RequestMapping("/admin/noticeList")
	 public String noticeList(Model model, @RequestParam(value="pageNum", defaultValue="1") int pageNum) {
			service.list(pageNum, model);
			return "admin/notice/noticeList";
		}
	 
	 @RequestMapping("/admin/noticeView")
	 public String noticeView(Model model, int num, int pageNum) {
		 	NoticeBoardDTO dto = service.readContent(num);
		 
		 	model.addAttribute("dto",dto);
		 	model.addAttribute("pageNum",pageNum);
			return "admin/notice/noticeView";
		}
	 
	 @RequestMapping("/admin/noticeDeletePro")
	 public String noticeDeletePro(Model model, int num,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		 	NoticeBoardDTO dto = service.readContent(num); // 공지사항 내용 조회
		    String content = dto.getContent(); // 공지사항의 내용

		    // 이미지 URL 추출
		    Pattern pattern = Pattern.compile("src\\s*=\\s*\"/resources/realImage/([^\"]+)\"");
		    Matcher matcher = pattern.matcher(content);

		    while (matcher.find()) {
		        String imageName = matcher.group(1); // 이미지 파일 이름 추출
		        File imageFile = new File(request.getServletContext().getRealPath("/resources/realImage/" + imageName));
		        if (imageFile.exists()) {
		            imageFile.delete(); // 파일 삭제
		        }
		    }
		    
		    try {
		    	service.delete(num); 
			    redirectAttributes.addFlashAttribute("deleteStatus", 1);
			} catch (Exception e) {
			    e.printStackTrace();
			    
			    redirectAttributes.addFlashAttribute("deleteStatus", 0);
			}
		  
		    return "redirect:/admin/noticeList";
		}
	 
	
	 //일반 유저들이 볼 수 있는 페이지 분류 
	 @RequestMapping("/nsb/noticeList")
	 public String userNoticeList(Model model, @RequestParam(value="pageNum", defaultValue="1") int pageNum) {
			service.list(pageNum, model);
			return "admin/notice/userNoticeList";
		}
	 
	 @RequestMapping("/nsb/noticeView")
	 public String userNoticeView(Model model, int num, int pageNum) {
		 	NoticeBoardDTO dto = service.readContent(num);
		 	model.addAttribute("dto",dto);
		 	model.addAttribute("pageNum",pageNum);
			return "admin/notice/userNoticeView";
		}
	 
}
	
	
	

