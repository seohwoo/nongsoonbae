package nong.soon.bae.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProductReadcountDTO {
	private String username;
	private String productnum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regdate;
	private String todaydate;
}
