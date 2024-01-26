package nong.soon.bae.contorller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import nong.soon.bae.bean.KakaoApproveResponse;
import nong.soon.bae.bean.KakaoReadyResponse;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.PaymentDTO;
import nong.soon.bae.service.KaKaoPayService;
import nong.soon.bae.service.TestService;

@RequestMapping("/test/*")
@RequiredArgsConstructor
@Controller
public class KaKaoController {

	@Autowired
	private final KaKaoPayService kakaoPayService;

	@Autowired
	private TestService service;
	
	/**
	 * 결제요청
	 */
	/*
	@RequestMapping("/ready")
	public @ResponseBody KakaoReadyResponse readyToKakaoPay(Principal pri, @RequestParam("isMembership") int isMembership) {
		String username = pri.getName();
		int cnt = service.findAddCartCnt(username);
		List<String> sellerList = Collections.EMPTY_LIST;
		List<MyPageDTO> cartList = Collections.EMPTY_LIST;
		PaymentDTO dto = new PaymentDTO();
		if (cnt > 0) {
			sellerList = service.findAddCartSeller(username);
			for (String seller : sellerList) {
				cartList = service.findAddCart(username, seller);
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
		return kakaoPayService.kakaoPayReady(dto);
	}

	@RequestMapping("/kakaomain")
	public String main(Model model) {
		int isMembership = 0;
		model.addAttribute("isMembership", isMembership);
		return "test/kakaomain";
	}

	@GetMapping("/success")
	public String afterPayRequest(@RequestParam("pg_token") String pgToken, Model model, Principal pri) {
		KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveResponse(pgToken);
		String username = pri.getName();
		List<String> sellerList = Collections.EMPTY_LIST;
		List<MyPageDTO> cartList = Collections.EMPTY_LIST;
		PaymentDTO dto = new PaymentDTO();
		sellerList = service.findAddCartSeller(username);
		int addCartCnt = service.findAddCartCnt(username);
		int cnt = 0;
		for (String seller : sellerList) {
			cartList = service.findAddCart(username, seller);
			for (MyPageDTO cartDTO : cartList) {
				dto.setUsername(cartDTO.getUsername());
				dto.setProductnum(cartDTO.getProductnum());
				dto.setOptionnum(cartDTO.getOptionnum());
				dto.setRealprice(cartDTO.getCount() * cartDTO.getPrice());
				dto.setTotalprice(dto.getRealprice());
				dto.setQuantity(cartDTO.getCount());
				if(kakaoApprove.getItem_name().equals("멤버쉽정기결제")) {
					dto.setSid(pgToken);
				}
			}
			cnt += service.insertUsersPayment(dto);
			if(cnt == addCartCnt) {
				service.deleteUsersAddCart(username);
			}
		}
		
		model.addAttribute("kakaoApprove", kakaoApprove);
		model.addAttribute("amount", kakaoApprove.getAmount());
		return "test/kakaosuccess";
	}

	/**
	 * 결제 진행 중 취소
	 */
	/*
	@GetMapping("/cancel")
	public void cancel() {

	}

	/**
	 * 결제 실패
	 */
	/*
	@GetMapping("/fail")
	public void fail() {

	}
	*/
}
