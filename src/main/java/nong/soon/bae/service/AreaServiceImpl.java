package nong.soon.bae.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ProductCategoryDTO;
import nong.soon.bae.repository.AreaMapper;

@Service
public class AreaServiceImpl implements AreaService{
	@Autowired
	private AreaMapper mapper;
	@Autowired
	private HashMap<String, String> categoryMap;

	@Override
	public void areaDetail(Model model, String area1, String area2) {
		categoryMap.put("area1",area1);
		categoryMap.put("area2",area2);
		int cnt = mapper.areaCnt(categoryMap);
		List<AreaDTO> list = Collections.EMPTY_LIST ;
		if (cnt > 0 ) {
		list = mapper.arealistdetail(area1);
		}
		model.addAttribute("area",list);
		model.addAttribute("cnt",cnt);
	}
	@Override
	public void arealist(Model model,String area1) {
		List<AreaDTO> list = mapper.arealistdetail(area1);
		model.addAttribute("list",list);
		//int totalProductCount = mapper.countAllProduct();
			}

	@Override
	public void arealistdetail(Model model, String area1, String area2) {
		categoryMap.put("area1",area1);
		int cnt = mapper.areaCnt(categoryMap);
		List<AllProductDTO> list = Collections.EMPTY_LIST ;
		List<AreaDTO> area = Collections.EMPTY_LIST ;
		if (cnt > 0 ) {
			area = mapper.arealistdetail(area1);
			list = mapper.arealistdetailpro(area1);
		}	
		model.addAttribute("area",area);
		System.out.println(area);
		model.addAttribute("list",list);
		System.out.println(list);
		model.addAttribute("cnt",cnt);
		System.out.println(cnt);
	}

	@Override
	public void findareaname(Model model, String selectedValue) {
		//model.addAttribute("areaname",areaname);
	}
	
	@Override
	public void areaallproduct(Model model) {
		List<AreaDTO> list = mapper.areaMenu();
		model.addAttribute("areamenu", list);
		
	}
	@Override
	public List<AreaDTO> areaMenu() {
		return mapper.areaMenu();
	}	
}
