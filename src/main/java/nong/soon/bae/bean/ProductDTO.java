package nong.soon.bae.bean;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProductDTO {
	private String optionnum;
	private String username;
	private String productnum;
	private String optionname;
	private int price;
	private int productcount;
	private int sellcount;
}