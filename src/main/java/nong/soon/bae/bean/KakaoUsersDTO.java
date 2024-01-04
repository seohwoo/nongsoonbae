package nong.soon.bae.bean;

import java.util.List;

import lombok.Data;

@Data
public class KakaoUsersDTO {
	private String username;
	private String password;
	private String nickname;
	private String name;
	private List<UserGradeDTO> grade;
	private String email;
	private String phone;
	private String birth;
	private String gender;
}