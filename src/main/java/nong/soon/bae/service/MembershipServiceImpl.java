package nong.soon.bae.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ChartDTO;
import nong.soon.bae.repository.MembershipMapper;

@Service
public class MembershipServiceImpl implements MembershipService{

	@Autowired
	private MembershipMapper mapper;
	@Autowired
	private HashMap<String, String> membershipMap;
	@Autowired
	private ArrayList<String> monthList;
	@Autowired
	private ArrayList<Double> valueList;
	@Autowired
	private ArrayList<Integer> userPriceList;
	@Autowired
	private ArrayList<String> userMarketList;
	
	@Override
	public void findDetailChart(Model model, String year, String month, String name, String value) {
		monthList.clear();
		valueList.clear();
		String keyword = "%" + year + month + "%";
		String day = year + month;
		membershipMap.put("keyword", keyword);
		membershipMap.put("name", name);
		int cnt = mapper.findDeatilChartCnt(membershipMap);
		int max = 0;
		List<ChartDTO> list = Collections.EMPTY_LIST;
		if(cnt > 0) {
			list = mapper.findDeatilChart(membershipMap);
			max = Integer.parseInt(String.valueOf(mapper.maxAvgPrice(membershipMap)).substring(0, 1))+1;
			for (ChartDTO dto : list) {
				monthList.add(dto.getRegdate());
				valueList.add(dto.getAvgprice());
			}
			monthList.add( day + "Æò±Õ");
			valueList.add(Double.parseDouble(value));
		}
		model.addAttribute("monthList", monthList);
		model.addAttribute("valueList", valueList);
		model.addAttribute("day", day);
		model.addAttribute("name", name);
		model.addAttribute("max", max);
	}

	@Override
	public void showUserPriceChart(Model model, String cate1, String cate2, String cate3) {
		userPriceList.clear();
		userMarketList.clear();
		membershipMap.put("cate1", cate1);
		membershipMap.put("cate2", cate2);
		membershipMap.put("cate3", cate3);
		int cnt = mapper.userCategoryChartCnt(membershipMap);
		List<AllProductDTO> list = Collections.EMPTY_LIST;
		String catename = "";
		int recentAvgPrice = 0;
		if(cnt > 0) {
			list = mapper.showUserCategoryChart(membershipMap);
			catename = list.get(0).getCatename();
			membershipMap.put("catename", catename);
			recentAvgPrice = (int) mapper.findRecentAvgPrice(membershipMap).get(0).getAvgprice();
			int isJoin = 0;
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getAvgprice() > recentAvgPrice && isJoin==0) {
					userPriceList.add(recentAvgPrice);
					userMarketList.add(catename +  "Æò±Õ°¡");
					isJoin++;
				}
				userPriceList.add(list.get(i).getAvgprice());
				userMarketList.add(list.get(i).getProductname());
				if(i == list.size()-1 && isJoin==0) {
					userPriceList.add(recentAvgPrice);
					userMarketList.add(catename +  "Æò±Õ°¡");
					isJoin++;
				}
			}
		}
		model.addAttribute("isChart", cnt);
		model.addAttribute("catename", catename);
		model.addAttribute("userPriceList", userPriceList);
		model.addAttribute("userMarketList", userMarketList);
	}
	
	
	

}
