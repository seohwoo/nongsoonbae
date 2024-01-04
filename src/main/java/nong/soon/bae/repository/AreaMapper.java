package nong.soon.bae.repository;

import java.util.HashMap;
import java.util.List;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.bean.AreaDTO;
public interface AreaMapper {
	public List<AreaDTO> arealistmore(int area1); //ÁßºÐ·ù
	public List<AllProductDTO> areaDetail(String area1,String area2);
	public List<AllProductDTO> areaCate (HashMap<String, String> map);
	
	public int areaCnt (int area1,int area2);
	public int productCnt(HashMap<String, String> map);
	
	public int count();
	public List<AreaDTO> arealist(HashMap map);
}
