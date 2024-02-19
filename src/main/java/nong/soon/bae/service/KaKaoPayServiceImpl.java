package nong.soon.bae.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import nong.soon.bae.bean.KakaoApproveResponse;
import nong.soon.bae.bean.KakaoReadyResponse;
import nong.soon.bae.bean.PaymentDTO;
import nong.soon.bae.data.ApiKeys;
import nong.soon.bae.data.FileRoot;


@Service
@RequiredArgsConstructor
@Transactional
public class KaKaoPayServiceImpl implements KaKaoPayService{
	
	static final String cid = "TC0ONETIME"; // 가맹점 테스트 코드
    static final String admin_Key = ApiKeys.getApiKeys().getKakaoPayKey(); // 공개 조심! 본인 애플리케이션의 어드민 키를 넣어주세요
    private KakaoReadyResponse kakaoReady;
	@Override
	public KakaoReadyResponse kakaoPayReady(PaymentDTO dto) {
		 // 카카오페이 요청 양식
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("sid", dto.getSid());
        parameters.add("partner_order_id", "가맹점 주문 번호");	// 가맹점 주문 번호
        parameters.add("partner_user_id", "가맹점 회원 ID");	// 가맹점 회원 ID
        parameters.add("item_name", dto.getItemname());				// 상품명
        parameters.add("item_code", "상품코드");				// 상품코드
        parameters.add("quantity", "" + dto.getQuantity());					// 상품개수
        parameters.add("created_at", "");					// 쓰지마(지우지마) : 결제요청시간
        parameters.add("approved_at", "");					// 쓰지마(지우지마) : 결제승인시간
        parameters.add("total_amount", "" + dto.getTotalprice());			// 실제가격
        parameters.add("tax_free_amount", "" + dto.getRealprice());			// 면세가격
        parameters.add("approval_url", "http://"+FileRoot.getIp()+":8080/user/pay/success"); // 성공 시 redirect url
        parameters.add("cancel_url", "http://"+FileRoot.getIp()+":8080/user/pay/cancel"); 	// 취소 시 redirect url
        parameters.add("fail_url", "http://"+FileRoot.getIp()+":8080/user/pay/fail"); 		// 실패 시 redirect url
         
        
        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        
        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        kakaoReady = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/ready",
                requestEntity,
                KakaoReadyResponse.class);
                
        return kakaoReady;
	}
	
	/**
     * 카카오 요구 헤더값
     */
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK " + admin_Key;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }
	
	@Override
	public KakaoApproveResponse ApproveResponse(String pgToken) {
		 // 카카오 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", kakaoReady.getTid());
        parameters.add("partner_order_id", "가맹점 주문 번호");
        parameters.add("partner_user_id", "가맹점 회원 ID");
        parameters.add("pg_token", pgToken);

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        
        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();
        
        KakaoApproveResponse approveResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/approve",
                requestEntity,
                KakaoApproveResponse.class);
                
        return approveResponse;
	}
    
    
    
	

}
