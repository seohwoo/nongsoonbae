package nong.soon.bae.service;


import nong.soon.bae.bean.KakaoApproveResponse;
import nong.soon.bae.bean.KakaoReadyResponse;

public interface KaKaoPayService {
	
	 public KakaoReadyResponse kakaoPayReady();
	 public KakaoApproveResponse ApproveResponse(String pgToken);

}
