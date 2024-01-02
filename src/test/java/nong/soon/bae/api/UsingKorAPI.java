package nong.soon.bae.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

<<<<<<< HEAD
public class UsingKorAPI {

	private String api_key = "";
=======
public class UsingKorAPI extends KorServiceAPI{

	private String api_key = getApi_key();
>>>>>>> branch 'shw/2' of https://github.com/seohwoo/nongsoonbae.git
	private URL url;
	BufferedReader bf;
	String result;
	
	private UsingKorAPI() {}
	
	private static UsingKorAPI instance = new UsingKorAPI();
	
	public static UsingKorAPI getInstance() { return instance; }

	public ArrayList<HashMap<String, String>> findLocation() {
		ArrayList<HashMap<String, String>> location = new ArrayList<HashMap<String, String>>();
		try {
			url = new URL("https://apis.data.go.kr/B551011/KorService1/areaCode1?numOfRows=30&pageNo=1&MobileOS=ETC&MobileApp=nongsoonbae&_type=json&serviceKey="+api_key);
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			result = bf.readLine();
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
			JSONObject response = (JSONObject) jsonObject.get("response");
			JSONObject body = (JSONObject) response.get("body");
			JSONObject items = (JSONObject) body.get("items");
			int totalCount = ((Long) body.get("totalCount")).intValue();
			JSONArray item = (JSONArray) items.get("item");
			for (int i = 0; i < totalCount; i++) {
				JSONObject arrItem = (JSONObject) item.get(i);
				HashMap<String, String> area = new HashMap<String, String>();
				area.put("name", arrItem.get("name").toString());
				area.put("code", arrItem.get("code").toString());
				location.add(area);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	}// findLocation()
	
	public ArrayList<HashMap<String, String>> findSubLocation(int areacode) {
		ArrayList<HashMap<String, String>> location = new ArrayList<HashMap<String, String>>();
		try {
			url = new URL("https://apis.data.go.kr/B551011/KorService1/areaCode1?numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=nongsoonbae&areaCode="+areacode+"&_type=json&serviceKey="+api_key);
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			result = bf.readLine();
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
			JSONObject response = (JSONObject) jsonObject.get("response");
			JSONObject body = (JSONObject) response.get("body");
			JSONObject items = (JSONObject) body.get("items");
			int totalCount = ((Long) body.get("totalCount")).intValue();
			JSONArray item = (JSONArray) items.get("item");
			for (int i = 0; i < totalCount; i++) {
				JSONObject arrItem = (JSONObject) item.get(i);
				HashMap<String, String> area = new HashMap<String, String>();
				area.put("name", arrItem.get("name").toString());
				area.put("code", arrItem.get("code").toString());
				location.add(area);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	}// findSubLocation()
	
}
