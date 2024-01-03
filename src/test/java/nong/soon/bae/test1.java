package nong.soon.bae;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import nong.soon.bae.api.UsingKorAPI;

public class test1 {

	
	public static void main(String[] args) {
		
		Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH/mm");
        String formattedDate = sdf.format(currentDate);
        System.out.println(formattedDate);
        String[] arDate = formattedDate.split("/");
        for (String date : arDate) {
			System.out.println(date);
		}
        
	}
	
	
}
