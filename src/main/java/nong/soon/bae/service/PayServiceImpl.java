package nong.soon.bae.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.PaymentDTO;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.repository.PayMapper;

@Service
public class PayServiceImpl implements PayService{
	
	@Autowired
	private PayMapper mapper;
	@Autowired
	private HashMap<String, String> paymentMap;
	@Autowired
	private PaymentDTO dto;
	
	
	@Override
	public PaymentDTO isProductReady(String username) {
		paymentMap.put("username", username);
		int cnt = mapper.findAddCartCnt(paymentMap);
		List<String> sellerList = Collections.EMPTY_LIST;
		List<MyPageDTO> cartList = Collections.EMPTY_LIST;
		if (cnt > 0) {
			sellerList = mapper.findAddCartSeller(paymentMap);
			for (String seller : sellerList) {
				paymentMap.put("seller", seller);
				cartList = mapper.findAddCart(paymentMap);
				for (int i = 0; i < cartList.size(); i++) {
					if (i == 0) {
						dto.setItemname(cartList.get(i).getOptionname());
					}
					dto.setRealprice(
							dto.getRealprice() + (cartList.get(i).getPrice() * cartList.get(i).getCount()));
					dto.setQuantity(dto.getQuantity() + cartList.get(i).getCount());
				}
			}
			if(dto.getRealprice()<50000) {
				dto.setTotalprice(dto.getRealprice()+3000);
			}else {
				dto.setTotalprice(dto.getRealprice());
			}
			dto.setItemname(dto.getItemname() + "외 " + (cnt-1) + "건");
		}
		return dto;
	}
	@Override
	public PaymentDTO isMembershipReady(String username) {
		paymentMap.put("username", username);
		int first = mapper.isFirstMembershipCnt(paymentMap);
			
		dto.setItemname("멤버십정기결제");
		dto.setQuantity(1);
		dto.setTotalprice(4900);
		dto.setRealprice(4900);
		if(first != 0) {
			dto.setSid(mapper.isFirstMembership(paymentMap).get(0).getSid());
		}
		return dto;
	}
	@Override
	public void isproductSuccess(String username) {
		paymentMap.put("username", username);
		List<String> sellerList = Collections.EMPTY_LIST;
		List<MyPageDTO> cartList = Collections.EMPTY_LIST;
		sellerList = mapper.findAddCartSeller(paymentMap);
		int addCartCnt = mapper.findAddCartCnt(paymentMap);
		int cnt = 0;
		for (String seller : sellerList) {
			paymentMap.put("seller", seller);
			cartList = mapper.findAddCart(paymentMap);
			for (MyPageDTO cartDTO : cartList) {
				dto.setUsername(cartDTO.getUsername());
				dto.setProductnum(cartDTO.getProductnum());
				dto.setOptionnum(cartDTO.getOptionnum());
				dto.setRealprice(cartDTO.getCount() * cartDTO.getPrice());
				dto.setTotalprice(dto.getRealprice());
				dto.setQuantity(cartDTO.getCount());
				dto.setSid("simple payment");
			}
			cnt += mapper.insertUsersPayment(dto);
			if(cnt == addCartCnt) {
				mapper.deleteUsersAddCart(username);
			}
		}
	}
	@Override
	public int isMembershipSuccess(String username, String sid) {
		dto.setUsername(username);
		dto.setProductnum("membership");
		dto.setOptionnum("membership");
		dto.setRealprice(4900);
		dto.setTotalprice(4900);
		dto.setQuantity(1);
		dto.setSid(sid);
		return mapper.insertUsersPayment(dto);
	}
	@Override
	public void changeGrade(String username) {
		String grade = mapper.isMembership(username).getGrade().get(0).getGrade();
		System.out.println(grade);
		String gradename = "멤버쉽";
		gradename = mapper.findGrade(gradename).getGrade();
		if(!grade.equals(gradename)) {
			paymentMap.put("username", username);
			paymentMap.put("gradename", gradename);
			mapper.changeGrade(paymentMap);
		}
	}
	@Override
	public UsersDTO isMembership(String username) {
		return mapper.isMembership(username);
	}
	
	@Override
	public void userQuitMembership(String username) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<PaymentDTO> lastMembershipPayDate(String username) {
		return mapper.lastMembershipPayDate(username);
	}
	
}
