package nong.soon.bae.service;

import java.util.List;

import org.springframework.ui.Model;

import nong.soon.bae.bean.AreaDTO;

public interface AreaService {
	public List<AreaDTO> catelist(String areaname); //��з�
	
	public List<AreaDTO> catelistarea(int area1); //�ߺз�


		
}
