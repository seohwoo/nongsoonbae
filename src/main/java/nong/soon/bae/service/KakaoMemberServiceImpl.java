package nong.soon.bae.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import nong.soon.bae.bean.KakaoUsersDTO;
import nong.soon.bae.contorller.KakaoController;
import nong.soon.bae.repository.SecurityMapper;

@Service
public class KakaoMemberServiceImpl implements KakaoMemberService {
	private static final Logger log = LoggerFactory.getLogger(KakaoController.class);
	
	@Autowired
    private SecurityMapper mapper;
	
	@Override
	public void kakaoJoin(KakaoUsersDTO usersDTO) {
		mapper.kakaoInsert(usersDTO);
        String userid = usersDTO.getUsername();
        log.info("userid:: " + userid);
    //    mapper.authorize(usersDTO);

	}

	@Override
	public KakaoUsersDTO kakaoLogin(String username) {
		log.info("username:: " + username);
        return mapper.kakaoSelect(username);
	}

	@Override
	public String findAuthBy(String userid) {
		log.info("userid:: " + userid);
        return mapper.findAuthBy(userid);
	}

	@Override
	public KakaoUsersDTO findByUserId(String username) {
		 return mapper.read(username);
	}

}
