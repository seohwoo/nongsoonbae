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
	public List<AreaDTO> areaMenu(Model model) {
	
		return mapper.arealist();
	}	
	@Override
	public void allproductlist(Model model) { //��ü�׸�
		int allCnt = mapper.allCnt();
		List<AllProductDTO> allprocuctList = Collections.EMPTY_LIST ;
		if(allCnt >0 ) {
			allprocuctList = mapper.allproductList();
		}
		model.addAttribute("allprocuctList",allprocuctList);
		model.addAttribute("allCnt",allCnt);
	}
	
	@Override
	public void arealistdeatil(Model model,String area1) { //area1 ����Ʈ
		List<AreaDTO> arealistdetail = mapper.arealistdetail(area1);
		model.addAttribute("arealistdetail",arealistdetail);
			}
	
	
	@Override
	public void areaprodutlist(Model model, String area1) {//area1 ��ǰ����Ʈ
		int cnt = mapper.productCnt(Integer.parseInt(area1));
		List<AllProductDTO> productlist = Collections.EMPTY_LIST ;
		if(cnt>0){
			productlist = mapper.productlist(area1);
		}
		
		model.addAttribute("cnt",cnt);
		System.out.println(cnt);
		model.addAttribute("productlist",productlist);
		System.out.println(productlist);
	}
	
	@Override
	public void areaprodictlistdetail(Model model, String area1, String area2) { //area2 ��ǰ����Ʈ
		categoryMap.put("area1", area1);
		categoryMap.put("area2", area2);
		int cntDetail = mapper.cntDetail(categoryMap);
		List<AllProductDTO> productlistdetail = Collections.EMPTY_LIST ; 
		if (cntDetail >0 ) {
			productlistdetail = mapper.productlistdetail(categoryMap);
		}
		model.addAttribute("cntDetail", cntDetail);
		model.addAttribute("productlistdetail",productlistdetail);
	}
}
