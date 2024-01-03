package nong.soon.bae.bean;

import java.util.List;

import lombok.Data;

@Data
public class UsersDTO {
	private String username;
	private String password;
	private String name;
	private List<UserGradeDTO> grade;
	private String email;
	private String birth;
	private int gender;
}
