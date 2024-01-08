package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ChartDTO;
import nong.soon.bae.bean.ProductCategoryDTO;

public interface MainMapper {
	
	public int seasonCategoryCnt(String keyword);
	public List<ProductCategoryDTO> seasonCategory(HashMap<String, String> map);
	public String findCatename(HashMap<String, String> map);
	public int seasonProductCnt(HashMap<String, String> map);
	public List<AllProductDTO> seasonProduct(HashMap<String, String> map);
	public double productChart(HashMap<String, String> map);
	public int maxAvgPrice(String catename);
	public List<ProductCategoryDTO> chartCategory(HashMap<String, String> map);
	public int chartCategoryCnt(String cate1);
	public List<ProductCategoryDTO> cateMenu();
	public ProductCategoryDTO nextCate(HashMap<String, String> map);
}
