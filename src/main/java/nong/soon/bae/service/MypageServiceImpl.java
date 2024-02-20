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
import nong.soon.bae.bean.PaymentDTO;
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
		model.addAttribute("likeList", list);
		model.addAttribute("likeNum", listNum);
		model.addAttribute("likeMaxNum", maxCategoryNum);
	}

	@Override
	public void selectFarmerDetail(String username, Model model, int listNum) {
		int categorySize = 10;
		int cnt = mapper.cntfarmer(username);
		List<MyPageDTO> user = selectfarmer(username);
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
		model.addAttribute("farmerList", list);
		model.addAttribute("farmerNum", listNum);
		model.addAttribute("farmerMaxNum", maxCategoryNum);
	}

	@Override
	public void deleteLike(String username, String productnum) {
		dto.setUsername(username);
		dto.setProductnum(productnum);
		mapper.deleteLike(dto);
	}

	@Override
	public void deleteFarmer(String username, String follow) {
		dto.setUsername(username);
		dto.setFollow(follow);
		mapper.deleteFarmer(dto);
	}

	@Override
	public void selectMyCart(String username, Model model) {
		ArrayList<MyPageDTO> list = new ArrayList<MyPageDTO>();
		List<MyPageDTO> cartCnt = mapper.findCartInfo(username);
		for (MyPageDTO myPageDTO : cartCnt) {
			HashMap<String, String> SelectMyCartMap = new HashMap<>();
		    SelectMyCartMap.put("username", myPageDTO.getUsername());
		    SelectMyCartMap.put("follow", myPageDTO.getFollow());
		    SelectMyCartMap.put("optionnum", myPageDTO.getOptionnum());
		    List<MyPageDTO> tempList = mapper.selectMyCart(SelectMyCartMap);
		    System.out.println(tempList);
		    for (MyPageDTO dto : tempList) {
		    	list.add(dto);
			}
		}
		System.out.println(list);
		model.addAttribute("MyCart", list);
	}

	@Override
	public void deleteCart(String username, String optionnum) {
		dto.setUsername(username);
		dto.setOptionnum(optionnum);
		mapper.deleteCart(dto);	
	}

	@Override
	public List<PaymentDTO> selectPay(String username) {
		return mapper.selectPay(username);
	}

	@Override
	public void selectPayDetail(String username, Model model) {
		List<PaymentDTO> pay = selectPay(username);
		List<MyPageDTO> list = new ArrayList<>();
		for (PaymentDTO PaymentDTO : pay) {
		    HashMap<String, String> SelectPayMap = new HashMap<>();
		    SelectPayMap.put("username", username);
		    SelectPayMap.put("follow", PaymentDTO.getFollow());
		    SelectPayMap.put("productnum", PaymentDTO.getProductnum());
		    SelectPayMap.put("orderdate", PaymentDTO.getFormatdate());
		    MyPageDTO dto = mapper.selectPayDetail(SelectPayMap);
		    list.add(dto);
		}
		model.addAttribute("paylist", list);
		
	}
	
}
