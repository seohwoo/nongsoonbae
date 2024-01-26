package nong.soon.bae.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.PaymentDTO;
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
			dto.setTotalprice(dto.getRealprice());
			dto.setItemname(dto.getItemname() + "외 " + (cnt-1) + "건");
		}
		return dto;
	}
	@Override
	public PaymentDTO isMembershipReady(String username) {
		PaymentDTO dto = null;
		paymentMap.put("username", username);
		int first = mapper.isFirstMembershipCnt(paymentMap);
		if(first==0) {
			dto = new PaymentDTO();
			dto.setItemname("멤버십정기결제");
			dto.setQuantity(1);
			dto.setTotalprice(4900);
			dto.setRealprice(4900);
		}else {
			dto = mapper.isFirstMembership(paymentMap).get(0);
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
			}
			cnt += mapper.insertUsersPayment(dto);
			if(cnt == addCartCnt) {
				mapper.deleteUsersAddCart(username);
			}
		}
	}
	@Override
	public void isMembershipSuccess(String username, String sid) {
		dto.setUsername(username);
		dto.setProductnum("membership");
		dto.setOptionnum("membership");
		dto.setRealprice(4900);
		dto.setTotalprice(4900);
		dto.setQuantity(1);
		dto.setSid(sid);
		mapper.insertUsersPayment(dto);
	}
	
}
