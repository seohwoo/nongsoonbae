package nong.soon.bae.service;
import java.util.List;
import org.springframework.ui.Model;
import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ProductCategoryDTO;

public interface CategoryService {
	public List<ProductCategoryDTO> cateMenu(Model model);  //대분류 조회
	public void allproductlist(Model model,String sort, int pageNum); //전체상품리스트
	public void catelistdeatil(Model model,String cate1); //중분류 카테고리 리스트
	
	//대분류 선택에 따른 상품 목록 ex: cate1 = 1 과일 상품 목록
	public void cateprodutlist (Model model,String cate1,int pageNum, String sort);
	//중분류 선택에 따른 상품 목록 ex: cate1 = 1 , cate2 = 1 감/딸기/토마토  상품 목록
	public void cateprodictlistdetail(Model model, String cate1, String cate2,
									int pageNum,String sort);
}
