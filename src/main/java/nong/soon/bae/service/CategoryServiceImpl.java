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
	public List<ProductCategoryDTO> catemenu(String catename) { //대분류 카테고리 조회 
		return mapper.catemenu(catename);
	}

	@Override
	public List<ProductCategoryDTO> catelist(int cate1) { //대분류 값 매개변수 두어 중분류 조회 
		return mapper.catelist(cate1);
	}
}
