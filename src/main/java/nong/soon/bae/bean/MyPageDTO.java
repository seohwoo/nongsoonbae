package nong.soon.bae.bean;

import lombok.Data;

@Data
public class MyPageDTO {
	private String username;
	private String follow;
	private String productnum;
	private String optionnum;
	private int count;
	private int wishstatus;
	
	private String otherUsername;
	//follow�� ���� �÷� ������ �Ⱦ��ٸ� �������ּ���
	
	private String filename;
	private String optionname;
	private String shopname;
	private int price;
	private String cate1;
	private String cate2;
	private String cate3;
	private String area1;
	private String area2;
}
