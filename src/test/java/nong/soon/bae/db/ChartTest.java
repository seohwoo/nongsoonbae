package nong.soon.bae.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ChartTest {

	@Autowired
	private DataSource ds;

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = null;
	
	@Test
	public void test() {
		int result = 0;
		ArrayList<Double> list = new ArrayList<Double>();
		sql = " select avg(totalprice) from chart where regdate like ? and name=? ";
		for (int i = 1; i <= 12; i++) {
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				String date = "%2023년" + i + "월%";
				pstmt.setString(1, date);
				pstmt.setString(2, "배추");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					result = (int) rs.getDouble(1);
					System.out.println(result);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null) {try {rs.close();}catch(Exception e) {e.printStackTrace();}}
				if(pstmt != null) {try {pstmt.close();}catch(Exception e) {e.printStackTrace();}}
				if(conn != null) {try {conn.close();}catch(Exception e) {e.printStackTrace();}}
			}
		}
	}
}
