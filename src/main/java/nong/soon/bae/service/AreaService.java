package nong.soon.bae.service;

import java.util.List;

import org.springframework.ui.Model;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;

public interface AreaService {
	public List<AreaDTO> catelist(String areaname); //��з�
	
	public List<AreaDTO> catelistarea(int area1); //�ߺз�

	public void areaDetail(Model model, String area1,String area2);
	
	
	public List<AreaDTO> arealist(); 
	public void arealistdetail(Model model,String area1);
	public void findareaname(Model model,String selectedValue);	
}
