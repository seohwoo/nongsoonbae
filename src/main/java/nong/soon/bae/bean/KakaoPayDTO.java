package nong.soon.bae.bean;

import lombok.Data;

@Data
public class KakaoPayDTO {
	
	private String cid;
	private String sid;
	private String partner_order_id;
	private String partner_user_id;
	private String item_name;
	private String item_code;
	private int quantity;
	private String created_at;
	private String approved_at;
	private int total_amount;
	private int tax_free_amount;
}
