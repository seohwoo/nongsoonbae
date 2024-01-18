package nong.soon.bae.bean;

import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class UserCheckDTO {
	private String username;
	private String password;
	private String name;
	private String grade;
	private String email;
	private String birth;
	private String phone;
	private int gender;
	private int regsite;
	private String reason;
	private String u_username;
	private String u_name;
	private String u_email;
	private String b_reason;
	private Date b_bandate;
	

}
