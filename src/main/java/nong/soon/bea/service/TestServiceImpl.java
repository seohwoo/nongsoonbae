package nong.soon.bea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nong.soon.bea.repository.TestMapper;

@Service
public class TestServiceImpl implements TestService{

	
	@Autowired
	private TestMapper mapper;
	
	@Override
	public int count() {
		return mapper.count();
	}

}
