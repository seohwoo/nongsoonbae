package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.PaymentDTO;
import nong.soon.bae.bean.ShopListDTO;

public interface MypageMapper {
	public List<MyPageDTO> selectLike(String username);
	public List<MyPageDTO> selectfarmer(String username);
	public List<MyPageDTO> selectcart(String username);
	public int cntlike(String username);
	public int cntfarmer(String username);
	public int cntcart(String username);
	
	public List<MyPageDTO> selectLikeDetail(HashMap map);
	public List<ShopListDTO> selectFarmerDetail(HashMap map);
	public List<MyPageDTO> selectMyCart(HashMap map);
	
	public void deleteLike(MyPageDTO dto);
	public void deleteFarmer(MyPageDTO dto);
	public void deleteCart(MyPageDTO dto);
	
	public List<PaymentDTO> selectPay(String username);
}
