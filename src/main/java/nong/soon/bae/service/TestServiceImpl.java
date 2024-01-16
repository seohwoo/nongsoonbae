package nong.soon.bae.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.repository.TestMapper;


@Service
public class TestServiceImpl implements TestService{

	
	@Autowired
	private TestMapper mapper;
	
	@Override
	public int count() {
		return mapper.count();
	}

	@Override
	public UsersDTO findUsers(String username) {
		return mapper.findUsers(username);
	}

}
