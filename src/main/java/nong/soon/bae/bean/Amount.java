package nong.soon.bae.bean;

import lombok.Data;

@Data
public class Amount {

	private int total; // �� ���� �ݾ�
    private int tax_free; // ����� �ݾ�
    private int tax; // �ΰ��� �ݾ�
    private int point; // ����� ����Ʈ
    private int discount; // ���αݾ�
    private int green_deposit; // �� ������
}
