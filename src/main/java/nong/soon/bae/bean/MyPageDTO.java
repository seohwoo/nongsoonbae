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
	//follow랑 같은 컬럼 같은데 안쓴다면 삭제해주세요
	
	private String filename;
	private String optionname;
	private String shopname;
	//결제할 때 조인에서 사용해요
	private int price;
}
