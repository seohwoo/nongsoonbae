package nong.soon.bae.bean;

import lombok.Data;

@Data
public class MainProductDTO {
	private String productnum;
	private String image;
	private String username;
	private String productname;
	private double stars;
	private int reviewcnt;
	private int wishcnt;
	private String areaname;
	private String categoryname;
	private String profile;
	private int avgprice;
	private int price;
}
