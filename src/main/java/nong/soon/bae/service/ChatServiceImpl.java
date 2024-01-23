package nong.soon.bae.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import nong.soon.bae.bean.ChatDTO;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.data.FileRoot;
import nong.soon.bae.repository.ChatMapper;

@Service
public class ChatServiceImpl implements ChatService{

	
	@Autowired
	private ChatMapper mapper;
	@Autowired
	private HashMap<String, String> chatMap;
	
	
	@Override
	public void userChatList(Model model, String username) {
		chatMap.put("username", username);
		int cnt = mapper.userChatCnt(chatMap);
		List<ChatDTO> list = Collections.EMPTY_LIST;
		if(cnt > 0) {
			list = mapper.userChatList(chatMap);
		}
		model.addAttribute("cnt", cnt);
		model.addAttribute("chatList", list);
	}

	@Override
	public void chatInfo(Model model, String chatno, String username) {
		String chat = "";
		String fileRoot = FileRoot.getFilepath();
		String filePath = "";
		String ip = FileRoot.getIp();
		boolean isAdmin = false;
		chatMap.put("chatno", chatno);
		chatMap.put("username", username);
		ChatDTO dto = mapper.chatInfo(chatMap);
		mapper.zeroNoRead(chatMap);
		int sendnoread = mapper.findSenduser(chatMap).getNoread();
		if(username!=null && dto.getSendname()!=null) {
			try {
				filePath = fileRoot+"\\"+getRoomIdentifier(username, dto.getSendname(), chatno)+".txt";
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
		if(dto.getSendname_grade().equals("ROLE_ADMIN")) {
			isAdmin = true;
		}
		model.addAttribute("chat", chat);
		model.addAttribute("dto", dto);
		model.addAttribute("ip", ip);
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("sendnoread", sendnoread);
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

	@Override
	public int updateNoRead(int cnt, int chatno, String username) {
		cnt++;
		mapper.updateNoRead(cnt, chatno, username);
		return cnt;
	}

	@Override
	public int updateJoinCnt(int joincnt, int chatno, int isjoin) {
		if(isjoin==1) {
			joincnt++;
		}else {
			joincnt--;
		}
		mapper.updateJoinCnt(joincnt, chatno);
		return joincnt;
	}

	@Override
	public ChatDTO createAdminRoom(String username) {
		String fileRoot = FileRoot.getFilepath();
		String filePath = "";
		UsersDTO admin = mapper.getAdminUser();
		chatMap.put("username", username);
		chatMap.put("sendname", admin.getUsername());
		int cnt = mapper.adminChatCnt(chatMap);
		if(cnt == 0) {
			mapper.insertQnaToMe(chatMap);
			mapper.insertQnaToSend(chatMap);
		}	
		ChatDTO dto = mapper.adminChatInfo(chatMap);
		try {
			filePath = fileRoot+"\\"+getRoomIdentifier(username, admin.getName(), String.valueOf(dto.getChatno()))+".txt";
			Path path = Paths.get(filePath);
			if(!Files.exists(path)) {
				Files.createFile(path);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
}
