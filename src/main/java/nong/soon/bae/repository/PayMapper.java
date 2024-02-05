package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.PaymentDTO;
import nong.soon.bae.bean.UserGradeDTO;
import nong.soon.bae.bean.UsersDTO;

public interface PayMapper {

	public int findAddCartCnt(HashMap<String, String> map);
	public List<String> findAddCartSeller(HashMap<String, String> map);
	public List<MyPageDTO> findAddCart(HashMap<String, String> map);
	public int isFirstMembershipCnt(HashMap<String, String> map);
	public List<PaymentDTO> isFirstMembership(HashMap<String, String> map);
	public int insertUsersPayment(PaymentDTO dto);
	public void deleteUsersAddCart(String username);
	public UsersDTO isMembership(String username);
	public UserGradeDTO findGrade(String gradename);
	public void changeGrade(HashMap<String, String> map);
	public List<PaymentDTO> lastMembershipPayDate(String username);
	
}
