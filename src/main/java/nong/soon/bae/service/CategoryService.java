package nong.soon.bae.service;
import java.util.List;
import org.springframework.ui.Model;
import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ProductCategoryDTO;

public interface CategoryService {
	public List<ProductCategoryDTO> catemenu(); //��з� ��ȸ
	public void catelist(Model model, String cate1, String cate2,String cate3 ); //�Һз� ��ȸ 
	
	public void cateDetail(Model model, String cate1,String cate2);
	public List<AllProductDTO >cateproduct();
}
