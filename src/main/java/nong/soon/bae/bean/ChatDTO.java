package nong.soon.bae.bean;

import lombok.Data;

@Data
public class ChatDTO {
	
	private int chatno;
	private String username;
	private String sendname;
	private String username_name;
	private String sendname_name;
	private String username_img;
	private String sendname_img;
	private String username_grade;
	private String sendname_grade;
	private int noread;
	private int isjoin;
}
