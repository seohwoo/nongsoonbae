package nong.soon.bae.repository;

import java.util.List;

import nong.soon.bae.bean.ProductCategoryDTO;

public interface CategoryMapper {
	public List<ProductCategoryDTO> catemenu(String catename); // ��з� 
	public List<ProductCategoryDTO> catelist(int cate1); //�ߺз�
}
