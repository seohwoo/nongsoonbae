package nong.soon.bae.service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.repository.MainMapper;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private MainMapper mapper;
	@Autowired
	private Date date;
	@Autowired
	private SimpleDateFormat simpleDateFormat;
	@Autowired
	private HashMap<String, String> seasonCategoryMap;

	public String[] todayInfo() {
		String formatDate = simpleDateFormat.format(date);
		String[] today = formatDate.split("/");
		return today;
	}
	
	@Override
	public void seasonCategory(Model model, int categoryNum) {
		String month = todayInfo()[1].substring(1);
		String keyword = "%"+month+",%";
		int categorySize = 10;
		int cnt = mapper.seasonCategoryCnt(keyword);
		int maxCategoryNum = (int) (cnt / categorySize) + (cnt % categorySize == 0 ? 0 : 1);
		if(categoryNum < 1) {
			categoryNum = 1;
		}else if(categoryNum > maxCategoryNum) {
			categoryNum = maxCategoryNum;
		}
		List<ProductCategoryDTO> list = Collections.EMPTY_LIST;
		if (cnt > 0) {
			int start = (categoryNum-1)*categorySize+1;
			int end = categoryNum * categorySize;
			seasonCategoryMap.put("keyword", keyword);
			seasonCategoryMap.put("start", String.valueOf(start));
			seasonCategoryMap.put("end", String.valueOf(end));
			list = mapper.seasonCategory(seasonCategoryMap);
		}
		model.addAttribute("categoryList", list);
		model.addAttribute("month", month);
		model.addAttribute("categoryNum", categoryNum);
	}

	@Override
	public void detailSeasonCategory(Model model, String cate1, String cate2, String cate3) {
		seasonCategoryMap.put("cate1", cate1);
		seasonCategoryMap.put("cate2", cate2);
		seasonCategoryMap.put("cate3", cate3);
		String catename = mapper.findCatename(seasonCategoryMap);
		int cnt = mapper.seasonProductCnt(seasonCategoryMap);
		List<AllProductDTO> list = Collections.EMPTY_LIST;
		if(cnt > 0) {
			list = mapper.seasonProduct(seasonCategoryMap);
		}
		model.addAttribute("catename", catename);
		model.addAttribute("productCnt", cnt);
		model.addAttribute("productList", list);
	}
	
	
	
	
}
