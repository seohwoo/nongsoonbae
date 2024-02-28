package nong.soon.bae.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AllProductDTO {
	private String productnum;
	private String productname;
	private String username;
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enddate;
	private int wishcnt;
	private int readcnt;
	private int avgprice;
	private int cate1;
	private int cate2;
	private int cate3;
	private int area1;
	private int area2;
	
	// 추가
	private String catenum;
	private String seqnum;
	private String filename;
	private int price;
	private String name;
	private int followers;
	private String address;
	
	// 카테고리 테이블에서 추가
	private String catename;
	private int amount;
	private String unit;
	
	private String usernames;
}