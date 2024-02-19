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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import nong.soon.bae.bean.ChatDTO;
import nong.soon.bae.data.FileRoot;
import nong.soon.bae.service.ChatService;

@Controller
@RequestMapping("/chat/*")
public class ChatController {
	
	
	@Autowired
	private ChatService service;
	
	
	@RequestMapping("list")
	public String roomList(Model model, Principal pri) {
		service.userChatList(model, pri.getName());
		return "user/chat/roomList";
	}
	
	@RequestMapping("room")
	public String chatRoom(Model model, Principal pri, String chatno) {
		service.chatInfo(model, chatno, pri.getName());
		return "user/chat/room";
	}
	 
	@PostMapping("/updateCount")
    @ResponseBody
    public String updateCount(@RequestParam("cnt") int cnt, 
    		@RequestParam("chatno") int chatno, @RequestParam("username") String username) {
        cnt = service.updateNoRead(cnt, chatno, username);
        return String.valueOf(cnt); 
    }
	
	@PostMapping("/updateJoin")
	@ResponseBody
	public String updateJoin(@RequestParam("joincnt") int joincnt, 
			@RequestParam("chatno") int chatno, @RequestParam("isjoin") int isjoin) {
		joincnt = service.updateJoinCnt(joincnt, chatno, isjoin);
		return String.valueOf(joincnt); 
	}
	
	@RequestMapping("admin")
	public String createRoom(Principal pri) {
		ChatDTO dto = service.createAdminRoom(pri.getName());
		return "redirect:/chat/room?chatno=" + dto.getChatno();
	}
	
	@RequestMapping("test")
	public String test() {
		return "/user/chat/test";
	}
	
	
}




