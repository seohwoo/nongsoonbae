package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;

public interface MypageMapper {
	public List<MyPageDTO> selectLike(String username);
	public List<MyPageDTO> selectfarmer(String username);
	public List<MyPageDTO> selectcart(String username);
	public int cntlike(String username);
	public int cntfarmer(String username);
	public int cntcart(String username);
	
	public List<MyPageDTO> selectLikeDetail(HashMap map);
	public List<MyPageDTO> selectFarmerDetail(HashMap map);
}
