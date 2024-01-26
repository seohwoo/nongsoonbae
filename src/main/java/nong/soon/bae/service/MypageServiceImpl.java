package nong.soon.bae.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	MyPageDTO dto;
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
			HashMap<String, String> likeDetailMap = new HashMap<>();
			likeDetailMap.put("username", myPageDTO.getFollow());
			likeDetailMap.put("productnum", myPageDTO.getProductnum());
			List<MyPageDTO> tempList = mapper.selectLikeDetail(likeDetailMap);
			list.addAll(tempList);
		}
		int start = (listNum - 1) * categorySize + 1;
		int end = Math.min(list.size(), listNum * categorySize);
		System.out.println((list.size()));
		model.addAttribute("likeList", list.subList(start, end));
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
		List<ShopListDTO> list = new ArrayList<>();
		for (MyPageDTO myPageDTO : user) {
			HashMap<String, String> farmerDetailMap = new HashMap<>();
			farmerDetailMap.put("username", myPageDTO.getFollow());
			List<ShopListDTO> tempList = mapper.selectFarmerDetail(farmerDetailMap);
			list.addAll(tempList);
		}
		int start = (listNum - 1) * categorySize + 1;
		int end = Math.min(list.size(), listNum * categorySize);
		model.addAttribute("farmerList", list.subList(start, end));
		model.addAttribute("farmerNum", listNum);
		model.addAttribute("farmerMaxNum", maxCategoryNum);
	}

	@Override
	public void deleteLike(String username, String productnum) {
		dto.setUsername(username);
		dto.setProductnum(productnum);
		System.out.println(dto);
		mapper.deleteLike(dto);
	}
	
	
}
