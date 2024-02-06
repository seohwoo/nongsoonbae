package nong.soon.bae.bean;


import java.util.Date;

import lombok.Data;

@Data
public class PaymentDTO {
	
	private String paymentnum;
	private String username;
	private String follow;
	private String productnum;
	private String optionnum;
	private String itemname;
	private int realprice;
	private int totalprice;
	private int quantity;
	private Date orderdate;
	private String sid;
	private int status;
}
