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
	//������ �� ���ο��� ����ؿ�
	private int price;
	//���Ÿ��
	private String productname;
	private int realprice;
	private Date orderdate;
}
