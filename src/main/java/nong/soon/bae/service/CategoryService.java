package nong.soon.bae.service;
import java.util.List;
import org.springframework.ui.Model;
import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ProductCategoryDTO;

public interface CategoryService {
	public List<ProductCategoryDTO> catemenu(String catename); //대분류 조회
	public List<ProductCategoryDTO> catelist(int cate1); //소분류 조회 
	
	public void cateDetail(Model model, String cate1,String cate2);
}
