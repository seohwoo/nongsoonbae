package nong.soon.bae.bean;
import java.util.Date;

import lombok.Data;

@Data
public class NoticeBoardDTO {
	private int num;
	private String title;
	private String content;
	private int readcount;
	private Date regdate;
	private int files;

}
