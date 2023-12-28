package nong.soon.bae.repository;

import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import nong.soon.bae.bean.UsersDTO;

public interface UsersRepository extends JpaRepository<Member, Long> {

	void save(UsersDTO users);
	
	Member FindByEmail(String email);

}
