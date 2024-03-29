package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ChartDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductListDTO;
import nong.soon.bae.bean.UsersDTO;

public interface MainMapper {
	/** 제철카테고리의 개수 */
	public int seasonCategoryCnt(String keyword);
	/** 제철카테고리의 리스트(10개씩 출력) */
	public List<ProductCategoryDTO> seasonCategory(HashMap<String, String> map);
	/** cate1,2,3으로 카테고리명 가져오기 */
	public String findCatename(HashMap<String, String> map);
	/** 해당 카테고리의 상품 개수 */
	public int seasonProductCnt(HashMap<String, String> map);
	/** 해당 카테고리의 상품 리스트(10개씩 출력) */
	public List<AllProductDTO> seasonProduct(HashMap<String, String> map);
	/** 해당 카테고리의 정보가 차트에 있는지 확인*/
	public int isChart(HashMap<String, String> map);
	/** 한달기준 농수산물의 평균 가격 차트*/
	public String productChart(HashMap<String, String> map);
	/** 해당 카테고리 중 가장 높은 평균 가격 */
	public int maxAvgPrice(String catename);
	/** 대분류 안에 있는 소분류 리스트(10개씩 출력) */
	public List<ProductCategoryDTO> chartCategory(HashMap<String, String> map);
	/** 대분류 안에 있는 소분류 개수 */
	public int chartCategoryCnt(String cate1);
	/** 대분류 안에 있는 중분류 리스트 */
	public List<ProductCategoryDTO> cateMenu();
	/** 다음 페이지의 카테고리 정보 */
	public ProductCategoryDTO nextCate(HashMap<String, String> map);
	/** 검색결과 개수 */
	public int searchProductCnt(String keyword);
	/** 검색결과 개수(광고) */
	public int searchAdProductCnt(String keyword);
	/** 검색결과 리스트(10개씩 출력) */
	public List<AllProductDTO> searchProduct(HashMap<String, String> map);
	/** 검색 결과 광고 4개 랜덤 출력*/
	public List<AllProductDTO> searchAdProduct(String keyword);
	/** 제철 광고 상품 갯수*/
	public int adCnt (HashMap<String, String> map);
	/** 제철 상품 인기,찜,가격순 정렬*/
	public List<AllProductDTO> readList (HashMap<String, String> map);
	public List<AllProductDTO> wishList (HashMap<String, String> map);
	public List<AllProductDTO> cheapList (HashMap<String, String> map);
	/** 제출 광고 상품 4개 랜덤 조회*/
	public List<AllProductDTO> adDetailSeason(HashMap<String, String> map);
	/** 로그인한 유저가 맴버쉽회원인지 조회 */
	public UsersDTO isMembership(String username);
	/** 전체 상품 리스트 중 한칸에 들어가는 모든 정보*/
	public ProductListDTO findProductListValue(HashMap<String, String> map);
}
