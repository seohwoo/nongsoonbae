package nong.soon.bae.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import nong.soon.bae.bean.AllProductDTO;
import nong.soon.bae.repository.AdMapper;


@Service
public class AdServiceImpl implements AdService {

	@Autowired
	private AdMapper mapper;
	@Autowired
	private HashMap<String, String> adMap;
	
	@Override
	public void myproduct(Model model,String username) {
		List<AllProductDTO> myproduct = Collections.EMPTY_LIST;
		myproduct = mapper.myproduct(username);
		model.addAttribute("myproduct",myproduct);
		
	}
}
