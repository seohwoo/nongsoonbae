package nong.soon.bae.bean;

import java.util.Date;

import lombok.Data;

@Data
public class PaymentDTO {
	private String username;
	private int price;
	private int totalprice;
	private String productnum;
	private String address;
	private int count;
	private Date orderdate;
	private int payment;
	private String optionname;
	private String phone;
}
