package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;

public interface MypageMapper {
	public MyPageDTO selectLike(String username);
	public MyPageDTO selectfarmer(String username);
	public int cntlike(String username);
	public int cntfarmer(String username);
	
	public List<ProductDTO> selectLikeDetail(HashMap map);
	public List<AllProductDTO> selectProductList(@Param("username") String username ,@Param("productnum") String productnum);
	public List<ShopListDTO> selectfarmerDetail(@Param("username") String username);
}
