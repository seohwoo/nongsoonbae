package nong.soon.bae;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import nong.soon.bae.api.UsingKorAPI;

public class test1 {

	
	public String[] todayInfo() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
		String formatDate = simpleDateFormat.format(date);
		String[] today = formatDate.split("/");
		return today;
	}
	
	public static void main(String[] args) {
		
		test1 t1 = new test1();
		String[] today = t1.todayInfo();
		for (String string : today) {
			System.out.println(string);
		}
        
	}
	
	
}
