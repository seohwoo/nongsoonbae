package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.ui.Model;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.bean.ProductCategoryDTO;

public interface AreaMapper {

	public int areaCnt (HashMap<String, String> map);
	public int areaallCnt ();
	public List<AreaDTO> arealist();
	public List<AllProductDTO> arealistdetailpro(String area1);
	public List<AreaDTO> arealistdetail (String area1);
	
	public List<AreaDTO> areaallList ();
	
	public List<AreaDTO> areaMenu();
	
	public int countAllProduct();
	
}
 