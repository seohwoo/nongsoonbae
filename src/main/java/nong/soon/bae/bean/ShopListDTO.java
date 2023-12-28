package nong.soon.bae.bean;

import java.util.Date;

import lombok.Data;

@Data
public class ShopListDTO {
	private String username;
	private String shopname;
	private String shopcontent;
	private Date regdate;
	private int visitcount;
	private String address;
	private String locx;
	private String locy;
	private int grade;
}
