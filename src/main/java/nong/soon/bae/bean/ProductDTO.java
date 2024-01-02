package nong.soon.bae.bean;

import java.util.Date;

import lombok.Data;

@Data
public class ProductDTO {
	private String productnum;
	private String productname;
	private int wishcount;
	private int totalprice;
	private int productcount;
	private int sellcount;
	private int imagecount;
	private String content;
	private int readcount;
	private Date startdate;
	private Date enddate;
	private int optionstatus;
}