package nong.soon.bae.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import nong.soon.bae.api.UsingKorAPI;

/**
 * area table insert
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class InsertAreaTable {
	
	@Autowired
	private DataSource ds;

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private String sql = null;
	
	UsingKorAPI api = UsingKorAPI.getInstance();
	
	//@Test
	public void test() {
		ArrayList<HashMap<String, String>> areaList = api.findLocation();
		sql = " insert into area(area1, areaname) values(?, ?) ";
		for (HashMap<String, String> areaMap : areaList) {
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(areaMap.get("code")));
				pstmt.setString(2, areaMap.get("name"));
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(pstmt != null) {try {pstmt.close();}catch(Exception e) {e.printStackTrace();}}
				if(conn != null) {try {conn.close();}catch(Exception e) {e.printStackTrace();}}
			}
		}
	}
	
	@Test
	public void test2() {
		ArrayList<Integer> areacodeList = new ArrayList<Integer>();
		ArrayList<HashMap<String, String>> areaList = api.findLocation();
		for (HashMap<String, String> areaMap : areaList) {
			areacodeList.add(Integer.parseInt(areaMap.get("code")));
		}
		
		sql = " insert into area(area1, area2, areaname) values(?, ?, ?) ";
		for (int areacode : areacodeList) {
			ArrayList<HashMap<String, String>> subAreaList = api.findSubLocation(areacode);
			for (HashMap<String, String> subAreaMap : subAreaList) {
				try {
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, areacode);
					pstmt.setInt(2, Integer.parseInt(subAreaMap.get("code")));
					pstmt.setString(3, subAreaMap.get("name"));
					pstmt.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					if(pstmt != null) {try {pstmt.close();}catch(Exception e) {e.printStackTrace();}}
					if(conn != null) {try {conn.close();}catch(Exception e) {e.printStackTrace();}}
				}
			}
		}
	
	}
	
	
	
	
}
