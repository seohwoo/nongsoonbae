package nong.soon.bae.service;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;

public interface MypageService {
	public MyPageDTO selectLike(String username);
	public MyPageDTO selectfarmer(String username);
	
	public ProductDTO selectLikeDetail(String username, String productnum);
	public AllProductDTO selectProductList(String username, String productnum);
	public ShopListDTO selectfarmerDetail(String username);
}
