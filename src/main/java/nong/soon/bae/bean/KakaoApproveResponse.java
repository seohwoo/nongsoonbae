package nong.soon.bae.bean;

import lombok.Data;

@Data
public class KakaoApproveResponse {

	private String aid; // ��û ���� ��ȣ
    private String tid; // ���� ���� ��ȣ
    private String cid; // ������ �ڵ�
    private String sid; // ��������� ID
    private String partner_order_id; // ������ �ֹ� ��ȣ
    private String partner_user_id; // ������ ȸ�� id
    private String payment_method_type; // ���� ����
    private Amount amount; // ���� �ݾ� ����
    private String item_name; // ��ǰ��
    private String item_code; // ��ǰ �ڵ�
    private int quantity; // ��ǰ ����
    private String created_at; // ���� ��û �ð�
    private String approved_at; // ���� ���� �ð�
    private String payload; // ���� ���� ��û�� ���� ���� ��, ��û �� ���� ����
	
}
