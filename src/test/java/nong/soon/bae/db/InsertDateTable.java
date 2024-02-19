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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class InsertDateTable {

	@Autowired
	private DataSource ds;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private String sql = null;
	
	
	@Test
	public void test() {
		String years[] = {"2022", "2023"};
		sql = " insert into monthdata values(?) ";
		for (String year : years) {
			for (int i = 1; i <= 12; i++) {
				try {
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					String date = year + "³â" + i + "¿ù";
					pstmt.setString(1, date);
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
