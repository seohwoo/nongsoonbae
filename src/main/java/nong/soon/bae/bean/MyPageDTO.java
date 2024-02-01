package nong.soon.bae.bean;

import java.util.Date;

import lombok.Data;

@Data
public class MyPageDTO {
	private String username;
	private String follow;
	private String productnum;
	private String optionnum;
	private int count;
	private int wishstatus;
	
	private String filename;
	private String optionname;
	private String shopname;
	//결제할 때 조인에서 사용해요
	private int price;
	//구매목록
	private String productname;
	private int realprice;
	private Date orderdate;
}
