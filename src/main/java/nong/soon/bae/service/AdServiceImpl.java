package nong.soon.bae.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import nong.soon.bae.bean.AdDTO;
import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.repository.AdMapper;


@Service
public class AdServiceImpl implements AdService {

	@Autowired
	private AdMapper mapper;
	@Autowired
	private HashMap adMap;
	
	@Override
	public void myproduct(Model model,String username) {
		List<AllProductDTO> myproduct = Collections.EMPTY_LIST;
		myproduct = mapper.myproduct(username);
		model.addAttribute("myproduct",myproduct);
		
	}

	@Override
	public void myAd(Model model, String username) {
		List<AdDTO> myAdList = Collections.EMPTY_LIST;
		myAdList = mapper.myAd(username);
		int myAdCnt = mapper.myAdCnt(username);
		model.addAttribute("myAdList",myAdList);
		model.addAttribute("myAdCnt",myAdCnt);
		
	}
	
	
	@Override
	public List<AdDTO> checkAd(String adSelect, String username) {
		adMap.clear();
		adMap.put("adSelect", adSelect);
		adMap.put("username", username);
		return mapper.checkAd(adMap);
	}

	
	
	@Override
	public void submitAd(String username, String adSelect, int daySelect, int price) {
		adMap.clear();
		adMap.put("username", username);
		adMap.put("adSelect", adSelect);
		adMap.put("daySelect", daySelect);
		adMap.put("price", price);
		mapper.submitAd(adMap);
		
	}

	@Override
	public void adList(Model model) {
		 List<AdDTO> adList = Collections.EMPTY_LIST;
		 adList = mapper.adList();
		 int submitCnt = mapper.submitCnt();
		 model.addAttribute("submitCnt",submitCnt);
		 model.addAttribute("adList",adList);
	}

	@Override
	public void adStart(String productnum,int days) {
		adMap.clear();
		adMap.put("productnum", productnum);
		adMap.put("days", days);
		mapper.adStart(adMap);
	}
	
	@Override
	public void adNo(String productnum, String username,int num) {
		adMap.clear();
		adMap.put("productnum", productnum);		
		adMap.put("username", username);
		adMap.put("num", num);
		mapper.adNo(adMap);
	}

	
	
	@Override
	public void updateStatus(String productnum) {
		mapper.updateStatus(productnum);
		
	}

	@Override
	public void adEndSoon(Model model) {
		List<AdDTO> endSoonList = Collections.EMPTY_LIST;
		endSoonList = mapper.adEndSoon();
		int endSoonCnt = mapper.endSoonCnt();
		model.addAttribute("endSoonCnt",endSoonCnt);
		model.addAttribute("endSoonList",endSoonList);
	}

	@Override
	public void adEnd(int num,String username, String productnum) {
		adMap.clear();
		adMap.put("num", num);
		adMap.put("username", username);
		adMap.put("productnum", productnum);
		mapper.adEnd(adMap);
		
	}

	@Override
	public void reUpdateStatus(String username, String productnum) {
		adMap.clear();
		adMap.put("username", username);
		adMap.put("productnum", productnum);
		mapper.reUpdateStatus(adMap);
	}

	

	
}
