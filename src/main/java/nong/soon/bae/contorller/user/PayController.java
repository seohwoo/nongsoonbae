package nong.soon.bae.contorller.user;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import nong.soon.bae.bean.KakaoApproveResponse;
import nong.soon.bae.bean.KakaoReadyResponse;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.PaymentDTO;
import nong.soon.bae.service.KaKaoPayService;
import nong.soon.bae.service.PayService;

@RequestMapping("/user/pay/*")
@RequiredArgsConstructor
@Controller
public class PayController {
	
	@Autowired
	private final KaKaoPayService kakaoPayService;
	
	@Autowired
	private PayService service;
	
	@Autowired
	private PaymentDTO paymentDTO;
	
	/**
	 * 결제요청
	 */
	@RequestMapping("/ready")
	public @ResponseBody KakaoReadyResponse readyToKakaoPay(Principal pri, @RequestParam("isMembership") int isMembership) {
		String username = pri.getName();
		if(isMembership==0) {
			paymentDTO = service.isProductReady(username);
		}else {
			paymentDTO = service.isMembershipReady(username);
		}
		return kakaoPayService.kakaoPayReady(paymentDTO);
	}

	@RequestMapping("/kakaopay")
	public String main(Model model) {
		int isMembership = 1;
		model.addAttribute("isMembership", isMembership);
		return "user/pay/kakaoBtn";
	}

	@GetMapping("/success")
	public String afterPayRequest(@RequestParam("pg_token") String pgToken, Model model, Principal pri) {
		KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveResponse(pgToken);
		String username = pri.getName();
		String sid = kakaoApprove.getSid();
		if(kakaoApprove.getItem_name().equals("멤버십정기결제")) {
			service.isMembershipSuccess(username, sid);
		}else {
			service.isproductSuccess(username);
		}
		model.addAttribute("kakaoApprove", kakaoApprove);
		model.addAttribute("amount", kakaoApprove.getAmount());
		return "user/pay/success";
	}

	/**
	 * 결제 진행 중 취소
	 */

	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:/user/pay/kakaopay";
	}

	/**
	 * 결제 실패
	 */
	@GetMapping("/fail")
	public String fail() {
		return "redirect:/user/pay/kakaopay";
	}

}
