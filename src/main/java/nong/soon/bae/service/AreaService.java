package nong.soon.bae.service;

import java.util.List;

import org.springframework.ui.Model;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ProductCategoryDTO;

public interface AreaService {
	
	public List<AreaDTO> areaMenu(Model model); 
	public void allproductlist(Model model);
	public void arealistdeatil(Model model,String area1);
	public void areaprodutlist (Model model,String area1);
	public void areaprodictlistdetail(Model model, String area1, String area2);
	
	
	
	
	
}
