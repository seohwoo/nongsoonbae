package nong.soon.bae.bean;

import lombok.Data;

@Data
public class AddressDTO {
	
	private String postcode;
	private String roadAddress;
	private String detailAddress;
	private String extraAddress;
}
