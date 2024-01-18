package nong.soon.bae.bean;

import java.util.Date;
import lombok.Data;
@Data
public class BlackListDTO {
	private String username;
	private String reason;
	private Date bandate;
	

}
