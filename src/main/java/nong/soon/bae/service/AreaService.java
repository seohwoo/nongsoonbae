package nong.soon.bae.service;

import java.util.List;

import org.springframework.ui.Model;

import nong.soon.bae.bean.AreaDTO;

public interface AreaService {
	public List<AreaDTO> arealistmore(int area1); //�ߺз�

	public void areaDetail(Model model, String area1,String area2);
	//public void arealist(int pageNum , Model model); // �� ���
	public int count(); 
	
}
