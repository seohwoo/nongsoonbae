package nong.soon.bae.bean;

import lombok.Data;

@Data
public class UsersDTO {
	private String username;
	private String password;
	private String name;
	private int grade;
	private String email;
	private String birth;
	private int gender;
}
