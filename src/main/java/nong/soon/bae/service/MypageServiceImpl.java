package nong.soon.bae.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.MyPageDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.bean.ProductDTO;
import nong.soon.bae.bean.ShopListDTO;
import nong.soon.bae.repository.MypageMapper;

@Service
public class MypageServiceImpl implements MypageService {

	@Autowired
	MypageMapper mapper;
	@Autowired
	private HashMap<String, String> LikeDetailMap;

	@Override
	public MyPageDTO selectLike(String username) {		
		return mapper.selectLike(username);
	}

	@Override
	public MyPageDTO selectfarmer(String username) {
		return mapper.selectfarmer(username);
	}

	@Override
	public void selectLikeDetail(String username, String productnum, Model model, int listNum) {
		int categorySize = 10;
		int cnt = mapper.cntlike(username);
		MyPageDTO user = selectLike(username);
		int maxCategoryNum = (int) (cnt / categorySize) + (cnt % categorySize == 0 ? 0 : 1);
		if(listNum < 1) {
			listNum = 1;
		}else if(listNum > maxCategoryNum) {
			listNum = maxCategoryNum;
		}
		List<ProductDTO> list = Collections.EMPTY_LIST;
		if (cnt > 0) {
			int start = (listNum-1)*categorySize+1;
			int end = listNum * categorySize;
			LikeDetailMap.put("start", String.valueOf(start));
			LikeDetailMap.put("end", String.valueOf(end));
			LikeDetailMap.put("username", user.getUsername());
			LikeDetailMap.put("productNum", productnum);
			list = mapper.selectLikeDetail(LikeDetailMap);
		}
		model.addAttribute("likeList", list);
		model.addAttribute("listNum", listNum);
		model.addAttribute("maxCategoryNum", maxCategoryNum);
	}

	@Override
	public List<AllProductDTO> selectProductList(String username, String productnum) {
		return mapper.selectProductList(username, productnum);
	}

	@Override
	public List<ShopListDTO> selectfarmerDetail(String username) {
		return mapper.selectfarmerDetail(username);
	}
}
