package nong.soon.bae.repository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import nong.soon.bae.bean.UserDetailsDTO;
import nong.soon.bae.bean.UserGradeDTO;
import nong.soon.bae.bean.UsersDTO;

public interface UsersRepository extends JpaRepository<Member, Long> {
	public UsersDTO login(String username);
	void save(UsersDTO users);
	String GetByAuth(String username);
	String regCheck(String username);
	UsersDTO FindByUser(String username);
	UsersDTO FindByEmail(String email);
	
	void createDetails(String username);
	void createReviews(String username);
	void createMypage(String username);
	void createPayment(String username);
	void createImages(String username);
	void addDetails(UserDetailsDTO details);
	void changeImg(UserDetailsDTO details);
	
	void changePass(@Param("password") String password,@Param("username") String username);
	
	int UserDelete(String username);
	String checkIfUserIsDeleted(String username);
}
