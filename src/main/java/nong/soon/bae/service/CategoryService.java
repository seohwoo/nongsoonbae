package nong.soon.bae.service;
import java.util.List;
import nong.soon.bae.bean.ProductCategoryDTO;

public interface CategoryService {
	public List<ProductCategoryDTO> catemenu(String catename); //대분류 조회
	public List<ProductCategoryDTO> catelist(int cate1); //소분류 조회 
	
}
