package nong.soon.bae.service;


import nong.soon.bae.bean.KakaoApproveResponse;
import nong.soon.bae.bean.KakaoReadyResponse;
import nong.soon.bae.bean.PaymentDTO;

public interface KaKaoPayService {
	
	 public KakaoReadyResponse kakaoPayReady(PaymentDTO dto);
	 public KakaoApproveResponse ApproveResponse(String pgToken);

}
