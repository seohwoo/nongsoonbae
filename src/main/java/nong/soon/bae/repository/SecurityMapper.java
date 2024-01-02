package nong.soon.bae.repository;

import org.apache.ibatis.annotations.Select;

import nong.soon.bae.bean.KakaoUsersDTO;
import nong.soon.bae.bean.UsersDTO;

public interface SecurityMapper {
	
	public UsersDTO login(String username);
	public void save(UsersDTO usersDTO);

	public KakaoUsersDTO read(String username);                     // 회원 정보 조회
	public void register(KakaoUsersDTO vo);                     // 회원 가입
	//public void authorize (KakaoUsersDTO memberVO);             // 회원 권한
	public void modify(KakaoUsersDTO vo);                     // 회원 정보 수정 
	public void remove(KakaoUsersDTO vo);                         // 회원 탈퇴
	public int checkPw(String userid, String userpw);     // 수정 및 삭제를 위한 비밀번호 체크

    /* 카카오 로그인 */

    // 카카오 회원가입
    void kakaoInsert(KakaoUsersDTO usersDTO);

    //snsId로 회원정보얻기
    @Select("SELECT name, email, birth FROM kakaousers WHERE username = #{username}")
    KakaoUsersDTO kakaoSelect(String username);

    //회원아이디로 권한찾기
    @Select("select gradename from kakaousers u, usergrade g where u.username = #{username}")
    String findAuthBy(String username);
}
