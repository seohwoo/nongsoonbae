package nong.soon.bae;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class DBTest {
	
	private static final String DRIVER ="oracle.jdbc.driver.OracleDriver";
    private static final String URL ="jdbc:oracle:thin:@192.168.219.123:1521/orcl"; 
    private static final String USER ="final02";
    private static final String PW ="final02";
	
    @Test
    public void testConnect() throws Exception{
        
        Class.forName(DRIVER);
        
        try(Connection con = DriverManager.getConnection(URL, USER, PW)){
            
            System.out.println(con);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
