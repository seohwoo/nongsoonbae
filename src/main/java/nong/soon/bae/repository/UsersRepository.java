package nong.soon.bae.repository;

import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import nong.soon.bae.bean.UserGradeDTO;
import nong.soon.bae.bean.UsersDTO;

public interface UsersRepository extends JpaRepository<Member, Long> {
	public UsersDTO login(String username);
	void save(UsersDTO users);
	void saveauth(UserGradeDTO grade);
	String GetByAuth(String username);
	
	Member FindByEmail(String email);
	

}
