package nong.soon.bae.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.log4j.Log4j;
import nong.soon.bae.contorller.KakaoController;
import nong.soon.bae.contorller.MemberController;

@Service
public class KakaoService {
	
	private static final Logger log = LoggerFactory.getLogger(KakaoService.class);
	
    public String getReturnAccessToken(String code) {
        String access_token = "";
        String refresh_token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

       try {
           URL url = new URL(reqURL);
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //HttpURLConnection ���� �� ����
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);


            // buffer ��Ʈ�� ��ü �� ���� �� ��û
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=25d3a749f71a3b11746ebbe324fa0eca");  //�� KEY VALUE
            sb.append("&redirect_uri=http://localhost:8080/login/oauth2/code/kakao"); // �� CALLBACK ���
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //  RETURN �� result ������ ����
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String br_line = "";
            String result = "";

            while ((br_line = br.readLine()) != null) {
                result += br_line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);


            // ��ū �� ���� �� ����
            access_token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_token;
    }

    public Map<String,Object> getUserInfo(String access_token) {
           Map<String,Object> resultMap =new HashMap<>();
           String reqURL = "https://kapi.kakao.com/v2/user/me";
            try {
                URL url = new URL(reqURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

               //��û�� �ʿ��� Header�� ���Ե� ����
                conn.setRequestProperty("Authorization", "Bearer " + access_token);

                int responseCode = conn.getResponseCode();
                System.out.println("responseCode : " + responseCode);


                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));

                String br_line = "";
                String result = "";


                while ((br_line = br.readLine()) != null) {
                    result += new String(URLDecoder.decode(br_line, "UTF-8"));
                }
               System.out.println("response:" + result);


                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(result);
                log.warn("element:: " + element);
                JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
                JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
                log.warn("id:: "+element.getAsJsonObject().get("id").getAsString());
                String id = element.getAsJsonObject().get("id").getAsString();
                String nickname = properties.getAsJsonObject().get("nickname").getAsString();
                String email = kakao_account.getAsJsonObject().get("email").getAsString();
                String name = kakao_account.getAsJsonObject().get("name").getAsString();
                String gender = kakao_account.getAsJsonObject().get("gender").getAsString();
                String birthyear = kakao_account.getAsJsonObject().get("birthyear").getAsString();
                String birthday = kakao_account.getAsJsonObject().get("birthday").getAsString();
                String phone_number = kakao_account.getAsJsonObject().get("phone_number").getAsString();
                
                log.warn("email:: " + email);
                resultMap.put("nickname", nickname);
                resultMap.put("id", id);
                resultMap.put("email", email);
                resultMap.put("name", name);
                resultMap.put("gender", gender);
                resultMap.put("birthyear", birthyear);
                resultMap.put("birthday", birthday);
                resultMap.put("phone_number", phone_number);
                

            } catch (IOException e) {
                e.printStackTrace();
            }
            return resultMap;
        }
}
