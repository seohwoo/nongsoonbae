package nong.soon.bae.service;

import java.util.List;

import org.springframework.ui.Model;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;

public interface MypageService {
	public List<MyPageDTO> selectLike(String username);
	public List<MyPageDTO> selectfarmer(String username);
	public List<MyPageDTO> selectcart(String username);
	
	public void selectLikeDetail(String username, Model model, int listNum);
	public void selectFarmerDetail(String username, Model model, int listNum);
	
	public void deleteLike(String username, String productnum);
	public void deleteFarmer(String username, String follow);
}
