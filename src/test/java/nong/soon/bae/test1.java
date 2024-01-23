package nong.soon.bae;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import nong.soon.bae.api.UsingKorAPI;

public class test1 {
	public static void main(String[] args) {
		
		Date date = new Date();
		SimpleDateFormat smf = new SimpleDateFormat("yyyy/MM/dd");
		
		String day = smf.format(date);		
		String year = day.split("/")[0].substring(2, 4);

		
		String productnum = "2411100001";
		
		productnum = String.valueOf(Long.parseLong(productnum) + (long) 1);
		System.out.println(productnum);
	}
	
	
	
}
