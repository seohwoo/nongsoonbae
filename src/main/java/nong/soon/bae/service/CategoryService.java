package nong.soon.bae.service;
import java.util.List;
import org.springframework.ui.Model;
import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ProductCategoryDTO;

public interface CategoryService {
	public List<ProductCategoryDTO> cateMenu(Model model); //대분류 조회
	public void allproductlist(Model model);
	public void catelistdeatil(Model model,String cate1);
	public void cateprodutlist (Model model,String cate1);
	public void cateprodictlistdetail(Model model, String cate1, String cate2);
}
