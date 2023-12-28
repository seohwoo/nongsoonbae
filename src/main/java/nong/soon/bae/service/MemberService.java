package nong.soon.bae.service;

import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import nong.soon.bae.bean.UsersDTO;
import nong.soon.bae.repository.UsersRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {
	private final UsersRepository usersRepository;

    public void save(UsersDTO users) {
        validateDuplicateMember(users);
        usersRepository.save(users);
    }

    private void validateDuplicateMember(UsersDTO users) {
    	Member findMember = usersRepository.FindByEmail(users.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("�̹� ���Ե� ȸ���Դϴ�.");
        }
    }
}