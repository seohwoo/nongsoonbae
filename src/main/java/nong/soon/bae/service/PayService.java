package nong.soon.bae.service;


import java.util.List;

import nong.soon.bae.bean.PaymentDTO;
import nong.soon.bae.bean.UsersDTO;

public interface PayService {
	
	public PaymentDTO isProductReady(String username);
	public PaymentDTO isMembershipReady(String username);
	public void isproductSuccess(String username);
	public int isMembershipSuccess(String username, String sid);
	public void changeGrade(String username);  
	public UsersDTO isMembership(String username);
	public void userQuitMembership(String username);
	public List<PaymentDTO> lastMembershipPayDate(String username);
	
	// Á¤·æ Ãß°¡
	public void isproductSuccess102(String follow);
	//
}
