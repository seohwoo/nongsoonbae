package nong.soon.bae.service;

import java.util.List;

import org.springframework.ui.Model;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;

public interface MypageService {
	public MyPageDTO selectLike(String username);
	public MyPageDTO selectfarmer(String username);
	
	public void selectLikeDetail(String username, String productnum, Model model, int listNum);
	public List<AllProductDTO> selectProductList(String username, String productnum);
	public List<ShopListDTO> selectfarmerDetail(String username);
}
