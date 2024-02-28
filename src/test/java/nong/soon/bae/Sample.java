package nong.soon.bae;

import java.util.ArrayList;
import java.util.HashMap;

import nong.soon.bae.api.UsingKorAPI;

public class Sample {

	
	public static void main(String[] args) {
		
		UsingKorAPI api = UsingKorAPI.getInstance();
		
		ArrayList<HashMap<String, String>> area = api.findLocation();
		 
		ArrayList<Integer> areacodeList = new ArrayList<Integer>();
		
		for (HashMap<String, String> areaMap : area) {
			areacodeList.add(Integer.parseInt(areaMap.get("code")));
		}
		ArrayList<HashMap<String, String>> subArea = api.findSubLocation(areacodeList.get(0));
		
		 for (HashMap<String, String> areaMap : subArea) {
			 System.out.println(areaMap.get("code"));
			 System.out.println(areaMap.get("name"));
			 System.out.println("========================"); 
		 }
	}
	
	
}
