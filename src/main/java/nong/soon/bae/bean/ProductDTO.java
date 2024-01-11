package nong.soon.bae.bean;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enddate;
	private int optionstatus;
	private String username;
	private String seqnum;
	
	private List<String> optionname;
    private List<Integer> optionprice;
}