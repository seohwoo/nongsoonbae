package nong.soon.bae.service;


import nong.soon.bae.bean.PaymentDTO;

public interface PayService {
	
	public PaymentDTO isProductReady(String username);
	public PaymentDTO isMembershipReady(String username);
	public void isproductSuccess(String username);
	public void isMembershipSuccess(String username, String sid);
}
