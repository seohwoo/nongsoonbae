package nong.soon.bae.repository;

import org.apache.ibatis.annotations.Select;

import nong.soon.bae.bean.KakaoUsersDTO;
import nong.soon.bae.bean.UserGradeDTO;
import nong.soon.bae.bean.UsersDTO;

public interface SecurityMapper {
	
	public UsersDTO login(String username);
	public void save(UsersDTO usersDTO);

	public KakaoUsersDTO read(String username);                     // ȸ�� ���� ��ȸ
	public void register(KakaoUsersDTO vo);                     // ȸ�� ����
	//public void authorize (KakaoUsersDTO memberVO);             // ȸ�� ����
	public void modify(KakaoUsersDTO vo);                     // ȸ�� ���� ���� 
	public void remove(KakaoUsersDTO vo);                         // ȸ�� Ż��
	public int checkPw(String userid, String userpw);     // ���� �� ������ ���� ��й�ȣ üũ
	
	String regCheck(String username);
	public String getgrade(String username);				//��� �ο��� ���� ���� ��ȸ

    /* īī�� �α��� */

    // īī�� ȸ������
    void kakaoInsert(KakaoUsersDTO usersDTO);
    public void savegrade(UserGradeDTO gradeDTO);

    //snsId�� ȸ���������
    @Select("SELECT name, email, birth FROM kakaousers WHERE username = #{username}")
    KakaoUsersDTO kakaoSelect(String username);

    //ȸ�����̵�� ����ã��
    @Select("select grade from kakaousers where username = #{username}")
    String findAuthBy(String username);
	
}
