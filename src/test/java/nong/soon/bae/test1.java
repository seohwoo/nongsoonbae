package nong.soon.bae;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import nong.soon.bae.api.UsingKorAPI;

public class test1 {

	/*
	public static double roundToDecimal(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(1, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }
	*/

	public static void main(String[] args) {
		
	//	System.out.println(roundToDecimal(351/100));
	//	System.out.println((int) 6321 / (roundToDecimal(351/100)));
		double avgPrice =  (double) 134 / 100;
		
		System.out.println(avgPrice); 
		avgPrice = (double) 8509 / avgPrice;
		System.out.println((int) avgPrice); 
		
		
		
		
		// avgPrice = (int) (optiontotalprice[0] / Math.round((optionunit[0] / optionamount) * 10.0) / 10.0);
		
	}
	
	
	
}
