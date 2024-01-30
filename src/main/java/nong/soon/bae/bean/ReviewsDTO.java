package nong.soon.bae.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReviewsDTO {	
	private String username;
	private String productnum;
	private String optionnum;
	private String content;
	private int imagecount;
	private int stars;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regdate;
}
