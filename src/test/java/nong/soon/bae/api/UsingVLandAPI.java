package nong.soon.bae.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import nong.soon.bae.data.ApiKeys;


public class UsingVLandAPI implements ApiKeys{
	
	public void usingVLandXY() {
		String apikey = vLandKey;
		String searchType = "road";
		String searchAddr = "경기도 고양시 덕양구 충경로 156 (행신동, 서정마을5단지아파트)";
		String epsg = "epsg:4326";

		StringBuilder sb = new StringBuilder("https://api.vworld.kr/req/address");
		sb.append("?service=address");
		sb.append("&request=getCoord");
		sb.append("&format=json");
		sb.append("&crs=" + epsg);
		sb.append("&key=" + apikey);
		sb.append("&type=" + searchType);
		sb.append("&address=" + URLEncoder.encode(searchAddr, StandardCharsets.UTF_8));

		try{
		    URL url = new URL(sb.toString());
		    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));

		    JSONParser jspa = new JSONParser();
		    JSONObject jsob = (JSONObject) jspa.parse(reader);
		    JSONObject jsrs = (JSONObject) jsob.get("response");
		    JSONObject jsResult = (JSONObject) jsrs.get("result");
		    JSONObject jspoitn = (JSONObject) jsResult.get("point");

		    System.out.println(jspoitn.get("x"));
		    System.out.println(jspoitn.get("y"));
		} catch (Exception e) {
		    throw new RuntimeException(e);
		}
	}
	
}
