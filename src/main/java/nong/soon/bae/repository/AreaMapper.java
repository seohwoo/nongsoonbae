package nong.soon.bae.repository;

import java.util.List;


import nong.soon.bae.bean.AreaDTO;

public interface AreaMapper {
	public List<AreaDTO> catelist(String areaname); //��з�
	public List<AreaDTO> catelistarea(int area1); //�ߺз�
}
