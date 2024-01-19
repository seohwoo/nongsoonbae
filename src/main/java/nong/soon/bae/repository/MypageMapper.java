package nong.soon.bae.repository;

import org.apache.ibatis.annotations.Param;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;

public interface MypageMapper {
	public MyPageDTO selectLike(String username);
	public MyPageDTO selectfarmer(String username);
	
	public ProductDTO selectLikeDetail(@Param("username") String username ,@Param("productnum") String productnum);
	public AllProductDTO selectProductList(@Param("username") String username ,@Param("productnum") String productnum);
	public ShopListDTO selectfarmerDetail(@Param("username") String username);
}
