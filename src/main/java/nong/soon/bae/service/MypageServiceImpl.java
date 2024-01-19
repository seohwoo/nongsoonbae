package nong.soon.bae.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;
import nong.soon.bae.repository.MypageMapper;

@Service
public class MypageServiceImpl implements MypageService {

	@Autowired
	MypageMapper mapper;

	@Override
	public MyPageDTO selectLike(String username) {		
		return mapper.selectLike(username);
	}

	@Override
	public MyPageDTO selectfarmer(String username) {
		return mapper.selectfarmer(username);
	}

	@Override
	public ProductDTO selectLikeDetail(String username, String productnum) {
		return mapper.selectLikeDetail(username, productnum);
	}

	@Override
	public AllProductDTO selectProductList(String username, String productnum) {
		return mapper.selectProductList(username, productnum);
	}

	@Override
	public ShopListDTO selectfarmerDetail(String username) {
		return mapper.selectfarmerDetail(username);
	}
}
