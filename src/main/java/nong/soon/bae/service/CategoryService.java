package nong.soon.bae.service;
import java.util.List;
import nong.soon.bae.bean.ProductCategoryDTO;

public interface CategoryService {
	public List<ProductCategoryDTO> catemenu(String catename); //��з� ��ȸ
	public List<ProductCategoryDTO> catelist(int cate1); //�Һз� ��ȸ 
	
}
