package nong.soon.bae.bean;

import lombok.Data;

@Data
public class KakaoReadyResponse {

	private String tid; // ���� ���� ��ȣ
    private String next_redirect_mobile_url; // ����� ���� ��� �޴� ���������� url
    private String next_redirect_pc_url; // pc ���� ��� �޴� ���� ������
    private String created_at;
}
