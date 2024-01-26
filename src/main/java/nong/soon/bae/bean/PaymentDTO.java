package nong.soon.bae.bean;


import lombok.Data;

@Data
public class PaymentDTO {
	
	private String paymentnum;
	private String username;
	private String productnum;
	private String optionnum;
	private String itemname;
	private int realprice;
	private int totalprice;
	private int quantity;
	private String orderdate;
	private String sid;
	
}
