package nong.soon.bae.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	@Autowired
	private ArrayList<Double> thislist;
	@Autowired
	private ArrayList<Double> lastlist;

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
		model.addAttribute("maxCategoryNum", maxCategoryNum);
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

	@Override
	public void showChart(Model model, String cate1, String cate2, String cate3) {
		if(thislist!=null && lastlist!=null) {
			thislist.clear();
			lastlist.clear();
		}
		String thisYear = String.valueOf((Integer.parseInt(todayInfo()[0])-1));
		String lastYear = String.valueOf((Integer.parseInt(todayInfo()[0])-2));
		seasonCategoryMap.put("cate1", cate1);
		seasonCategoryMap.put("cate2", cate2);
		seasonCategoryMap.put("cate3", cate3);
		String catename = mapper.findCatename(seasonCategoryMap);
		seasonCategoryMap.put("catename", catename);
		int max = mapper.maxAvgPrice(catename);
		String strMax = String.valueOf(max);
		int yValue = Integer.parseInt(strMax.substring(0,1))+1;
		String chartY = String.valueOf(yValue);
		for (int i = 0; i < strMax.length()-1; i++) {
			chartY += "0";
		}
		yValue = Integer.parseInt(chartY);
		String keyword = "";
		for (int i = 1; i <= 12; i++) {
			keyword = "%" + thisYear + "년" + i + "월%";
			seasonCategoryMap.put("keyword", keyword);
			thislist.add(mapper.productChart(seasonCategoryMap));
			keyword = "%" + lastYear + "년" + i + "월%";
			seasonCategoryMap.put("keyword", keyword);
			lastlist.add(mapper.productChart(seasonCategoryMap));
		}
		model.addAttribute("catename", catename);
		model.addAttribute("thisYear", thisYear);
		model.addAttribute("lastYear", lastYear);
		model.addAttribute("yValue", yValue);
		model.addAttribute("thislist", thislist);
		model.addAttribute("lastlist", lastlist);
	}
	
	public void page(int pageSize, int pageNum) {
		int start = (pageNum-1)*pageSize+1;
		int end = pageNum * pageSize;
		seasonCategoryMap.put("start", String.valueOf(start));
		seasonCategoryMap.put("end", String.valueOf(end));
	}
	

	@Override
	public void showCategory(Model model, String cate1, String cate2, String cate3, int categoryNum) {
		int categorySize = 10;
		int cnt = mapper.chartCategoryCnt(cate1);
		int maxCategoryNum = (int) (cnt / categorySize) + (cnt % categorySize == 0 ? 0 : 1);
		if(categoryNum < 1) {
			categoryNum = 1;
		}else if(categoryNum > maxCategoryNum) {
			categoryNum = maxCategoryNum;
		}
		
		List<ProductCategoryDTO> list = Collections.EMPTY_LIST;
		if (cnt > 0) {
			seasonCategoryMap.put("cate1", cate1);
			page(categorySize, categoryNum);
			list = mapper.chartCategory(seasonCategoryMap);
			categoryNum++;
		}
		page(categorySize, categoryNum);
		ProductCategoryDTO dto = null;
		if(mapper.nextCate(seasonCategoryMap) != null) {
			dto = mapper.nextCate(seasonCategoryMap);
			model.addAttribute("cate2", dto.getCate2());
			model.addAttribute("cate3", dto.getCate3());
		}
		categoryNum--;
		
		model.addAttribute("cateList", list);
		model.addAttribute("cate1", cate1);
		model.addAttribute("categoryNum", categoryNum);
		model.addAttribute("maxCategoryNum", maxCategoryNum);
		
	}

	@Override
	public void cateMenu(Model model) {
		List<ProductCategoryDTO> list = mapper.cateMenu();
		model.addAttribute("catemenu", list);
	}
	
	
	
	
}
