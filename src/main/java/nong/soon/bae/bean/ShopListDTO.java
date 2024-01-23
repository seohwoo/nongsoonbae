package nong.soon.bae.bean;

import java.util.Date;

import lombok.Data;

@Data
public class ShopListDTO {
	private String username;
	private String shopname;
	private String shopcontent;
	private Date regdate;
	private String address;
	private int grade;
}
