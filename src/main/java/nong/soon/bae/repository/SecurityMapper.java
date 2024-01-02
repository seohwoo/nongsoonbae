package nong.soon.bae.repository;

import org.apache.ibatis.annotations.Select;

import nong.soon.bae.bean.KakaoUsersDTO;
import nong.soon.bae.bean.UsersDTO;

public interface SecurityMapper {
	
	public UsersDTO login(String username);
	public void save(UsersDTO usersDTO);

	KakaoUsersDTO read(String userid);                     // ȸ�� ���� ��ȸ
    void register(KakaoUsersDTO vo);                     // ȸ�� ����
    void authorize (KakaoUsersDTO memberVO);             // ȸ�� ����
    void modify(KakaoUsersDTO vo);                     // ȸ�� ���� ���� 
    void remove(KakaoUsersDTO vo);                         // ȸ�� Ż��
    int checkPw(String userid, String userpw);     // ���� �� ������ ���� ��й�ȣ üũ

    /* īī�� �α��� */

    // īī�� ȸ������
    void kakaoInsert(KakaoUsersDTO usersDTO);

    //snsId�� ȸ���������
    @Select("SELECT name, email, birth FROM kakaousers WHERE username = #{username}")
    KakaoUsersDTO kakaoSelect(String username);

    //snsId�� ȸ�� ���̵�ã��
    @Select("SELECT name FROM kakaousers WHERE username = #{username}")
    String findUserIdBy2(String username);

    //ȸ�����̵�� ����ã��
    @Select("select gradename from kakaousers u, usergrade g where u.username = #{username}")
    String findAuthBy(String username);
}
