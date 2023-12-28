package nong.soon.bae.bean;

import lombok.Data;

@Data
public class ReviewsDTO {	
	private String num;
	private String name;
	private String productnum;
	private String content;
	private int imagecount;
	private int stars;
}
