package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ChartDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.UsersDTO;

public interface MainMapper {
	/** ��öī�װ��� ���� */
	public int seasonCategoryCnt(String keyword);
	/** ��öī�װ��� ����Ʈ(10���� ���) */
	public List<ProductCategoryDTO> seasonCategory(HashMap<String, String> map);
	/** cate1,2,3���� ī�װ��� �������� */
	public String findCatename(HashMap<String, String> map);
	/** �ش� ī�װ��� ��ǰ ���� */
	public int seasonProductCnt(HashMap<String, String> map);
	/** �ش� ī�װ��� ��ǰ ����Ʈ(10���� ���) */
	public List<AllProductDTO> seasonProduct(HashMap<String, String> map);
	/** �ش� ī�װ��� ������ ��Ʈ�� �ִ��� Ȯ��*/
	public int isChart(HashMap<String, String> map);
	/** �Ѵޱ��� ����깰�� ��� ���� ��Ʈ*/
	public double productChart(HashMap<String, String> map);
	/** �ش� ī�װ� �� ���� ���� ��� ���� */
	public int maxAvgPrice(String catename);
	/** ��з� �ȿ� �ִ� �Һз� ����Ʈ(10���� ���) */
	public List<ProductCategoryDTO> chartCategory(HashMap<String, String> map);
	/** ��з� �ȿ� �ִ� �Һз� ���� */
	public int chartCategoryCnt(String cate1);
	/** ��з� �ȿ� �ִ� �ߺз� ����Ʈ */
	public List<ProductCategoryDTO> cateMenu();
	/** ���� �������� ī�װ� ���� */
	public ProductCategoryDTO nextCate(HashMap<String, String> map);
	/** �˻���� ���� */
	public int searchProductCnt(String keyword);
	/** �˻���� ����Ʈ(10���� ���) */
	public List<AllProductDTO> searchProduct(HashMap<String, String> map);
	/** �α����� ������ �ɹ���ȸ������ ��ȸ */
	public UsersDTO isMembership(String username);
}
