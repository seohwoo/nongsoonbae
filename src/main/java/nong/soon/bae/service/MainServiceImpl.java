package nong.soon.bae.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nong.soon.bae.repository.MainMapper;

@Service
public class MainServiceImpl implements MainService {

	
	@Autowired
	private MainMapper mapper;
	
	
	
	
}
