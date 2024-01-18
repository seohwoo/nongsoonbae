package nong.soon.bae.contorller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import nong.soon.bae.bean.ChatDTO;
import nong.soon.bae.data.ApiKeys;
import nong.soon.bae.service.TestService;


@Controller
@RequestMapping("/test/*")
public class TestController{
	
	private static final Logger log = LoggerFactory.getLogger(TestController.class);
	
	private ApiKeys apikey = ApiKeys.getApiKeys();
	
	@Autowired
	private TestService service;
	@Autowired
	private ArrayList<String> srcValues;
	@Autowired
	private ArrayList<String> realFiles;
	
	
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
	
	public static String encode(String memberId, String secretKey, String algorithms) {
		  try {
		    Mac mac = Mac.getInstance(algorithms);
		    mac.init(new SecretKeySpec(hexify(secretKey), algorithms));

		    byte[] hash = mac.doFinal(memberId.getBytes());

		    StringBuilder sb = new StringBuilder(hash.length * 2);
		    for (byte b: hash) {
		      sb.append(String.format("%02x", b));
		    }

		    return sb.toString();
		  }catch (Exception e) {
		    throw new RuntimeException(e);
		  }
		}

		private static byte[] hexify(String string) {
		  return DatatypeConverter.parseHexBinary(string);
		}
	
	@RequestMapping("channel")
	public String channel(Model model) {
		model.addAttribute("pluginKey", apikey.getPluginkey());
		model.addAttribute("secretKey", apikey.getChaSecretKey());
		return "/test/channelTalk";
	}
	
	@RequestMapping("summer")
	public String summer() {
		return "/test/summer";
	}
	
	
	@RequestMapping("roomList")
	public String roomList(Model model, Principal pri) {
		
		String username = pri.getName();
		List<ChatDTO> chatList = service.userChatList(username);
		model.addAttribute("chatList", chatList);
		return "/test/roomList";
	}
	
	@RequestMapping("room")
	public String chatRoom(Model model, Principal pri, String sendname, String num) throws Exception {
		String username = pri.getName();
		String chat = "";
		String fileRoot = "D:\\dvsp\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\nongsoonbae\\resources\\chatRoom\\";
		String filePath = "";
		if(username!=null && sendname!=null) {
			try {
				filePath = fileRoot+"\\"+getRoomIdentifier(username, sendname)+".txt";
				Path path = Paths.get(filePath);
				if(!Files.exists(path)) {
					Files.createFile(path);
				}
				File file = new File(filePath);
				Scanner sc = new Scanner(file);
				while (sc.hasNextLine()) {
					chat += sc.nextLine();
				}
			} catch (IOException e) {
	        	e.printStackTrace();
	        }
		}
		chat = changeChat(chat, username, sendname);
		model.addAttribute("chat", chat);
		model.addAttribute("username", username);
		model.addAttribute("sendname", sendname);
		return "test/room";
	}
	
	private String getRoomIdentifier(String username, String sendname) {
        String sortedNames = Stream.of(username, sendname)
                .sorted()
                .collect(Collectors.joining("_to_"));
        return sortedNames;
    }
	
	// 채팅 UI 변경
	private String changeChat(String chat, String username, String sendname) {
		String result = "";
		String[] arChat = chat.split(",");
		
		for (int i = 0; i < arChat.length; i++) {
			if(i%3==2) {
				if(arChat[i-2].equals(username)) {
					result += "<div class='msg right-msg'><div class='msg-img' style='background-image: url(https://image.flaticon.com/icons/svg/145/145867.svg)'></div><div class='msg-bubble'><div class='msg-info'><div class='msg-info-name'>"+ username +"</div><div class='msg-info-time'>"+arChat[i]+"</div></div><div class='msg-text'>"+arChat[i-1]+"</div></div></div>";
				}else {
					result += "<div class='msg left-msg'><div class='msg-img' style='background-image: url(https://image.flaticon.com/icons/svg/327/327779.svg)'></div><div class='msg-bubble'><div class='msg-info'><div class='msg-info-name'>"+sendname+"</div><div class='msg-info-time'>"+arChat[i]+"</div></div><div class='msg-text'>"+arChat[i-1]+"</div></div></div>";
				}
			}
		}
		return result;
	}
	
	@RequestMapping("roomdesign")
	public String roomdesign(Model model) {
		Date date = new Date();
		model.addAttribute("date", date);
		return "/test/roomdesign";
	}
	
	
	//써머노트 사용할려면 여기부터....
	@RequestMapping("editorPro")
	public String editorPro(String content, Model model,String[] fileNames, HttpServletRequest request) {
		String fileRoot = request.getServletContext().getRealPath("/resources/summernoteImage/");
		String realRoot = request.getServletContext().getRealPath("/resources/realImage/");
		int cnt = 1;
		content = content.replace("src=\"/resources/summernoteImage/", "src=\"/resources/realImage/");
	    if(fileNames != null) {
	    	isFile(fileNames, content);
			for (String filename : realFiles) {
				try {
					File sourceFile = new File(fileRoot+filename);
					File targetDirectory = new File(realRoot);
					String ext = filename.substring(filename.lastIndexOf("."));
					String realname = "productnum_"+cnt+ext;
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
		model.addAttribute("content", content);
		return "/test/editorPro";
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
	//...여기까지
}
