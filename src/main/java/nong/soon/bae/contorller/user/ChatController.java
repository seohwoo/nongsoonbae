package nong.soon.bae.contorller.user;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nong.soon.bae.bean.ChatDTO;
import nong.soon.bae.data.FileRoot;
import nong.soon.bae.service.ChatService;

@Controller
@RequestMapping("/chat/*")
public class ChatController {
	
	
	@Autowired
	private ChatService service;
	
	
	@RequestMapping("roomList")
	public String roomList(Model model, Principal pri) {
		String username = pri.getName();
		List<ChatDTO> chatList = service.userChatList(username);
		model.addAttribute("chatList", chatList);
		return "/test/roomList";
	}
	
	@RequestMapping("room")
	public String chatRoom(Model model, Principal pri, String sendname, String chatno) throws Exception {
		String username = pri.getName();
		String chat = "";
		String fileRoot = FileRoot.getFilepath();
		String filePath = "";
		String ip = FileRoot.getIp();
		ChatDTO dto = service.chatInfo(chatno, username);
		service.zeroNoRead(Integer.parseInt(chatno), username);
		int sendnoread = service.findSenduser(chatno, username).getNoread();
		
		if(username!=null && sendname!=null) {
			try {
				filePath = fileRoot+"\\"+getRoomIdentifier(username, sendname, chatno)+".txt";
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
		chat = changeChat(chat, dto);
		model.addAttribute("chat", chat);
		model.addAttribute("dto", dto);
		model.addAttribute("ip", ip);
		model.addAttribute("sendnoread", sendnoread);
		return "test/room";
	}
	
	private String getRoomIdentifier(String username, String sendname, String chatno) {
        String sortedNames = Stream.of(username, sendname)
                .sorted()
                .collect(Collectors.joining("_to_"));
        sortedNames = sortedNames + "_" + chatno;
        return sortedNames;
    }
	
	// 채팅변화
	private String changeChat(String chat, ChatDTO dto) {
		String result = "";
		String[] arChat = chat.split(",");
		
		for (int i = 0; i < arChat.length; i++) {
			if(i%3==2) {
				if(arChat[i-2].equals(dto.getUsername_name())) {
					result += "<div class='msg right-msg'><div class='msg-img' style='background-image: url(/resources/file/profile/"+dto.getUsername_img()+")'></div><div class='msg-bubble'><div class='msg-info'><div class='msg-info-name'>"+ arChat[i-2] +"</div><div class='msg-info-time'>"+arChat[i]+"</div></div><div class='msg-text'>"+arChat[i-1]+"</div></div></div>";
				}else {
					result += "<div class='msg left-msg'><div class='msg-img' style='background-image: url(/resources/file/profile/"+dto.getSendname_img()+")'></div><div class='msg-bubble'><div class='msg-info'><div class='msg-info-name'>"+arChat[i-2]+"</div><div class='msg-info-time'>"+arChat[i]+"</div></div><div class='msg-text'>"+arChat[i-1]+"</div></div></div>";
				}
			}
		}
		return result;
	}
	
	
	
}
