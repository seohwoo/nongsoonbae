package nong.soon.bae.service;

import java.util.List;

import org.springframework.ui.Model;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.PaymentDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;
import nong.soon.bae.bean.UsersDTO;

public interface MypageService {
	public UsersDTO findNameInMyPage(String username);
	public List<MyPageDTO> selectLike(String username);
	public List<MyPageDTO> selectfarmer(String username);
	public List<MyPageDTO> selectcart(String username);
	
	public void selectLikeDetail(String username, Model model, int listNum);
	public void selectFarmerDetail(String username, Model model, int listNum);
	public void selectMyCart(String username, Model model);
	
	public void deleteLike(String username, String productnum);
	public void deleteFarmer(String username, String follow);
	public void deleteCart(String username, String optionnum);
	
	public List<PaymentDTO> selectPay(String username);
	public void selectPayDetail(String username, Model model);
}
