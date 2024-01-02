package nong.soon.bae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.repository.CategoryMapper;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryMapper mapper;

	@Override
	public List<ProductCategoryDTO> catemenu(String catename) { //��з� ī�װ� ��ȸ 
		return mapper.catemenu(catename);
	}

	@Override
	public List<ProductCategoryDTO> catelist(int cate1) { //��з� �� �Ű����� �ξ� �ߺз� ��ȸ 
		return mapper.catelist(cate1);
	}
}
