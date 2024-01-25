package nong.soon.bae.service;

import java.util.ArrayList;
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
	@Autowired
	private HashMap<String, String> FarmerDetailMap;
	@Autowired
	private HashMap<String, String> SelectMyCartMap;

	@Override
	public List<MyPageDTO> selectLike(String username) {		
		return mapper.selectLike(username);
	}

	@Override
	public List<MyPageDTO> selectfarmer(String username) {
		return mapper.selectfarmer(username);
	}
	@Override
	public List<MyPageDTO> selectcart(String username) {
		return mapper.selectcart(username);
	}

	@Override
	public void selectLikeDetail(String username, Model model, int listNum) {
		int categorySize = 10;
		int cnt = mapper.cntlike(username);
		List<MyPageDTO> user = selectLike(username);
		int maxCategoryNum = (int) (cnt / categorySize) + (cnt % categorySize == 0 ? 0 : 1);
		if(listNum < 1) {
			listNum = 1;
		}else if(listNum > maxCategoryNum) {
			listNum = maxCategoryNum;
		}
		List<MyPageDTO> list = new ArrayList<>();
		for (MyPageDTO myPageDTO : user) {
			int start = (listNum-1)*categorySize+1;
			int end = listNum * categorySize;
			LikeDetailMap.put("start", String.valueOf(start));
			LikeDetailMap.put("end", String.valueOf(end));
			LikeDetailMap.put("username", myPageDTO.getFollow());
			LikeDetailMap.put("productNum", myPageDTO.getProductnum());
			List<MyPageDTO> tempList = mapper.selectLikeDetail(LikeDetailMap);
			list.addAll(tempList);
		}

		model.addAttribute("likeList", list);
		model.addAttribute("likeNum", listNum);
		model.addAttribute("likeMaxNum", maxCategoryNum);
	}

	@Override
	public void selectFarmerDetail(String username, Model model, int listNum) {
		int categorySize = 10;
		int cnt = mapper.cntfarmer(username);
		List<MyPageDTO> user = selectLike(username);
		int maxCategoryNum = (int) (cnt / categorySize) + (cnt % categorySize == 0 ? 0 : 1);
		if(listNum < 1) {
			listNum = 1;
		}else if(listNum > maxCategoryNum) {
			listNum = maxCategoryNum;
		}
		List<MyPageDTO> list = new ArrayList<>();
		for (MyPageDTO myPageDTO : user) {
			int start = (listNum-1)*categorySize+1;
			int end = listNum * categorySize;
			FarmerDetailMap.put("start", String.valueOf(start));
			FarmerDetailMap.put("end", String.valueOf(end));
			FarmerDetailMap.put("username", myPageDTO.getFollow());
			FarmerDetailMap.put("productNum", myPageDTO.getProductnum());
			List<MyPageDTO> tempList = mapper.selectFarmerDetail(LikeDetailMap);
			list.addAll(tempList);
		}
		model.addAttribute("farmereList", list);
		model.addAttribute("farmerNum", listNum);
		model.addAttribute("farmerMaxNum", maxCategoryNum);
	}
	
	

	
}
