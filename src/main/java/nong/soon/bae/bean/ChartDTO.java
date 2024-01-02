package nong.soon.bae.bean;

import lombok.Data;

@Data
public class ChartDTO {
	
	private int num;
	private String name;
	private String regdate;
	private double totalprice;
	private double avgprice;
	private double sellcount;
	private int cate1;
	
}
