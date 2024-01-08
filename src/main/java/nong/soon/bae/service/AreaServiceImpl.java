package nong.soon.bae.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
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
		List<AllProductDTO> area = Collections.EMPTY_LIST ;
		area = mapper.arealistdetail(area1);
		model.addAttribute("area",area);
		model.addAttribute("cnt",cnt);
	}
	@Override
	public List<AreaDTO> arealist() {
		
		return mapper.arealist();
	}

	@Override
	public void arealistdetail(Model model, String area1) {
		categoryMap.put("area1",area1);
		int cnt = mapper.areaCnt(categoryMap);
		List<AllProductDTO> list = Collections.EMPTY_LIST ;
		if (cnt > 0 ) {
			list = mapper.arealistdetail(area1);
		}	
		model.addAttribute("list",list);
		System.out.println(list);
		model.addAttribute("cnt",cnt);
		System.out.println(cnt);
	}

	@Override
	public void findareaname(Model model, String selectedValue) {
		//model.addAttribute("areaname",areaname);
	}
}
