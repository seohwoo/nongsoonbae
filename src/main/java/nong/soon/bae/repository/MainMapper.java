package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ChartDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductListDTO;
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
	public String productChart(HashMap<String, String> map);
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
	/** �˻���� ����(����) */
	public int searchAdProductCnt(String keyword);
	/** �˻���� ����Ʈ(10���� ���) */
	public List<AllProductDTO> searchProduct(HashMap<String, String> map);
	/** �˻� ��� ���� 4�� ���� ���*/
	public List<AllProductDTO> searchAdProduct(String keyword);
	/** ��ö ���� ��ǰ ����*/
	public int adCnt (HashMap<String, String> map);
	/** ��ö ��ǰ �α�,��,���ݼ� ����*/
	public List<AllProductDTO> readList (HashMap<String, String> map);
	public List<AllProductDTO> wishList (HashMap<String, String> map);
	public List<AllProductDTO> cheapList (HashMap<String, String> map);
	/** ���� ���� ��ǰ 4�� ���� ��ȸ*/
	public List<AllProductDTO> adDetailSeason(HashMap<String, String> map);
	/** �α����� ������ �ɹ���ȸ������ ��ȸ */
	public UsersDTO isMembership(String username);
	/** ��ü ��ǰ ����Ʈ �� ��ĭ�� ���� ��� ����*/
	public ProductListDTO findProductListValue(HashMap<String, String> map);
}
