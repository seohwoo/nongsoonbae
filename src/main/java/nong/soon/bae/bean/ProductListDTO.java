package nong.soon.bae.bean;

import java.util.Date;

import lombok.Data;

@Data
public class ProductListDTO {
	private String username;
	private String productnum;
	private String productname;
	private String user_name;
	private String user_image;
	private Date startdate;
	private Date enddate; 
	private int wishcnt;
	private int readcnt;
	private int min_price; 
	private double avg_stars;
	private int review_count;
	private String cate_name;
	private String area_name1;
	private String area_name2;
	private String image_filename;
}
