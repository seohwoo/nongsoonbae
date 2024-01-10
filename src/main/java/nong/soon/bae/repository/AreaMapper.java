package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;

public interface AreaMapper {
	public List<AreaDTO> catelist(String areaname); //��з�
	public List<AreaDTO> catelistarea(int area1); //�ߺз�
	public int areaCnt (HashMap<String, String> map);
	
	public List<AreaDTO> arealist();
	public List<AllProductDTO> arealistdetail(String area1);
	
	public String findarea(String selectedValue);
	
}
