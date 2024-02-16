package nong.soon.bae.service;
import java.util.List;
import org.springframework.ui.Model;
import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ProductCategoryDTO;

public interface CategoryService {
	public List<ProductCategoryDTO> cateMenu(Model model);  //��з� ��ȸ
	public void allproductlist(Model model,String sort, int pageNum); //��ü��ǰ����Ʈ
	public void catelistdeatil(Model model,String cate1); //�ߺз� ī�װ� ����Ʈ
	
	//��з� ���ÿ� ���� ��ǰ ��� ex: cate1 = 1 ���� ��ǰ ���
	public void cateprodutlist (Model model,String cate1,int pageNum, String sort);
	//�ߺз� ���ÿ� ���� ��ǰ ��� ex: cate1 = 1 , cate2 = 1 ��/����/�丶��  ��ǰ ���
	public void cateprodictlistdetail(Model model, String cate1, String cate2,
									int pageNum,String sort);
}
