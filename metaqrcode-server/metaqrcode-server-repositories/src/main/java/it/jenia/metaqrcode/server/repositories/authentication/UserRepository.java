package it.jenia.metaqrcode.server.repositories.authentication;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.jenia.metaqrcode.server.entities.authentication.User;

public interface UserRepository extends JpaRepository<User, BigInteger> {

	@Query("select u "
			+ "from User u "
			+ "where u.email = :email") 
	public User searchByEmail(@Param("email") String email);

	@Query("select u "
			+ "from User u "
			+ "where u.nickName = :nickName") 
	public User searchByNickName(@Param("nickName") String nickName);

}
