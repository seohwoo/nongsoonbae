package nong.soon.bae.service;

import nong.soon.bae.bean.KakaoUsersDTO;

import nong.soon.bae.bean.UserGradeDTO;

public interface KakaoMemberService {
	
	public void kakaoJoin(KakaoUsersDTO usersDTO);
	public KakaoUsersDTO kakaoLogin(String username);
	public String findAuthBy(String userid);
	public KakaoUsersDTO findByUserId(String userid);
	public String getgrade(String username);
	public void setgrade(UserGradeDTO gradeDTO);
}
