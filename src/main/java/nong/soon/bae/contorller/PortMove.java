package nong.soon.bae.contorller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


public class PortMove extends HttpServlet{
	
	 private int portnum = 0;
	 
	 public int getPortnum() {
		return portnum;
	}

	public void setPortnum(int portnum) {
		this.portnum = portnum;
	}

	@Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // 'param1'�� 'param2' �Ķ���� ���� ����
	        String num = request.getParameter("num");
	        setPortnum(Integer.parseInt(num));
	    }


}
