package nong.soon.bae.contorller;

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
import nong.soon.bae.service.KaKaoPayService;

@RequestMapping("/test/*")
@RequiredArgsConstructor
@Controller
public class KaKaoController {

	private final KaKaoPayService kakaoPayService;
    
    /**
     * 결제요청
     */
    @RequestMapping("/ready")
    public @ResponseBody KakaoReadyResponse readyToKakaoPay() {
    	
        return kakaoPayService.kakaoPayReady();
    }
    @RequestMapping("/kakaomain")
    public String main() {
    	return "test/kakaomain";
    }
    @GetMapping("/success")
    public String afterPayRequest(@RequestParam("pg_token") String pgToken,Model model) {
        KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveResponse(pgToken);
        model.addAttribute("kakaoApprove", kakaoApprove);
        model.addAttribute("amount", kakaoApprove.getAmount());
        return "test/kakaosuccess";
    }
    
    
    /**
     * 결제 진행 중 취소
     */
    
    @GetMapping("/cancel")
    public void cancel() {

    }

    /**
     * 결제 실패
     */
    @GetMapping("/fail")
    public void fail() {

    }
	
	
}
