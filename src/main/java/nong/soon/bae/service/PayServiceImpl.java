package nong.soon.bae.service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
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
	@Autowired
    private TaskScheduler taskScheduler;
	@Autowired
	private SimpleDateFormat simpleDateFormat;
	
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
				// 정룡 추가
				dto.setFollow(cartDTO.getFollow());
				//
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
	
	// 정룡 추가
	@Override
	public void isproductSuccess102(String follow) {
		paymentMap.put("follow", follow);
		List<String> sellerList = Collections.EMPTY_LIST;
		List<MyPageDTO> cartList = Collections.EMPTY_LIST;
		sellerList = mapper.findAddCartSeller(paymentMap);
		int addCartCnt = mapper.findAddCartCnt(paymentMap);
		int cnt = 0;
		for (String seller : sellerList) {
			paymentMap.put("seller", seller);
			cartList = mapper.findAddCart(paymentMap);
			for (MyPageDTO cartDTO : cartList) {
				dto.setFollow(cartDTO.getUsername());
				dto.setUsername(cartDTO.getFollow());
				dto.setProductnum(cartDTO.getProductnum());
				dto.setOptionnum(cartDTO.getOptionnum());
				dto.setRealprice(cartDTO.getCount() * cartDTO.getPrice());
				dto.setTotalprice(dto.getRealprice());
				dto.setQuantity(cartDTO.getCount());
				dto.setSid("simple payment");
			}
			cnt += mapper.insertUsersPayment102(dto);
			
			if(cnt == addCartCnt) {
				mapper.deleteUsersAddCart(follow);
			}
		}
	}
	//
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
	
	/** 특정 시간이 되면 실행되는 코드 */
	public void timeToChangeGrade(String cronExpression) {
		String[] arCorn = cronExpression.split("/");
		int month = Integer.parseInt(arCorn[1])+1 > 12 ? 1 : Integer.parseInt(arCorn[1])+1;
		cronExpression = "0 0 0 " + Integer.parseInt(arCorn[2]) + " " + month + " *";
		taskScheduler.schedule(() -> {
            mapper.changeGrade(paymentMap);
            System.out.println("해지완료.... today is : " + new Date());
        }, new CronTrigger(cronExpression));
    }
	
	
	@Override
	public void userQuitMembership(String username) {
		String gradename = "회원";
		gradename = mapper.findGrade(gradename).getGrade();
		if(mapper.isMembership(username).getGrade().get(0).getGrade().equals("ROLE_MEMBERSHIP")) {
			paymentMap.put("username", username);
			paymentMap.put("gradename", gradename);
			String cronExpression = simpleDateFormat.format(mapper.lastMembershipPayDate(username).get(0).getOrderdate());
			timeToChangeGrade(cronExpression);
			String lastsid = mapper.lastMembershipPayDate(username).get(0).getSid();
			String newsid = "멤버쉽 해지";
			paymentMap.put("lastsid", lastsid);
			paymentMap.put("newsid", newsid);
			mapper.updateRegularPayment(paymentMap);
		}
	}
	@Override
	public List<PaymentDTO> lastMembershipPayDate(String username) {
		return mapper.lastMembershipPayDate(username);
	}
	
}
