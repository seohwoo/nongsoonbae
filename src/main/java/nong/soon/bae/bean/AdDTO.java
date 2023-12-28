package nong.soon.bae.bean;

import java.util.Date;

import lombok.Data;

@Data
public class AdDTO {
	private int num;
	private String username;
	private String productname;
	private Date startdate;
	private Date enddate;
	private int readcount;
	private int price;
}
