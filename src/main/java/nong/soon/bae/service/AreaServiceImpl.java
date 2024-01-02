package nong.soon.bae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nong.soon.bae.bean.AreaDTO;
import nong.soon.bae.repository.AreaMapper;

@Service
public class AreaServiceImpl implements AreaService{

	@Autowired
	private AreaMapper mapper;

	@Override
	public List<AreaDTO> catelist(String areaname) {
		return mapper.catelist(areaname);
	}

	@Override
	public List<AreaDTO> catelistarea(int area1) {
		
		return mapper.catelistarea(area1);
	}
}
