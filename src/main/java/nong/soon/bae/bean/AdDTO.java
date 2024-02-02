package nong.soon.bae.bean;

import java.util.Date;

import lombok.Data;

@Data
public class AdDTO {
	private int num;
	private String username;
	private String productnum;
	private String productname;
	private Date submitdate;
	private Date startdate;
	private Date enddate;
	private int days;
	private int price;
	private int adstatus;
}
