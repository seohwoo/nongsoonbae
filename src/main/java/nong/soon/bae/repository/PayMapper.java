package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.PaymentDTO;

public interface PayMapper {

	public int findAddCartCnt(HashMap<String, String> map);
	public List<String> findAddCartSeller(HashMap<String, String> map);
	public List<MyPageDTO> findAddCart(HashMap<String, String> map);
	public int isFirstMembershipCnt(HashMap<String, String> map);
	public List<PaymentDTO> isFirstMembership(HashMap<String, String> map);
	public int insertUsersPayment(PaymentDTO dto);
	public void deleteUsersAddCart(String username);
	
}
