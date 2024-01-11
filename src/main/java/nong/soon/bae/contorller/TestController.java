package nong.soon.bae.contorller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import nong.soon.bae.service.TestService;


@Controller
@RequestMapping("/test/*")
public class TestController {
	
	private static final Logger log = LoggerFactory.getLogger(TestController.class);
	
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
	public String editorPro(String content, Model model,String[] fileNames, HttpServletRequest request) {
		String fileRoot = request.getServletContext().getRealPath("/resources/summernoteImage/");
		String realRoot = request.getServletContext().getRealPath("/resources/realImage/");
		int cnt = 1;
		
		content = content.replace("src=\"/resources/summernoteImage/", "src=\"/resources/realImage/");
		ArrayList<String> srcValues = extractSrcValues(content);
	    // Ãâ·Â
	    for (String src : srcValues) {
	    	src = src.replaceAll("/resources/realImage/", "");
	    	System.out.println(src);
	    }
		
		for (String filename : fileNames) {
			try {
		        File sourceFile = new File(fileRoot+filename);
		        File targetDirectory = new File(realRoot);
		        String ext = filename.substring(filename.lastIndexOf("."));
		        Files.copy(sourceFile.toPath(), targetDirectory.toPath().resolve(""+cnt+ext), StandardCopyOption.REPLACE_EXISTING);
		        cnt++;
		        
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
		model.addAttribute("content", content);
		return "/test/editorPro";
	}
	
	 public ArrayList<String> extractSrcValues(String jspCode) {
	     Pattern pattern = Pattern.compile("src\\s*=\\s*\"([^\"]+)\"");
	     Matcher matcher = pattern.matcher(jspCode);

	     ArrayList<String> srcValues = new ArrayList<String>();
	     while (matcher.find()) {
	         srcValues.add(matcher.group(1));
	     }
	     return srcValues;
	 }
	
	
	@RequestMapping("summer")
	public String summer() {
		return "/test/summer";
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
	
}
