package cn.qlq.springData;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User2, Integer> {
	// select * from user2 where username=? and address=?
	User2 findByUsernameAndAddress(String username, String address);

	List<User2> findByUsernameLike(String username);

	long countByUsernameLike(String username);

	List<User2> findByCountryId(Integer countryId);

	@Query("from User2 where username = ?1 and address = ?2 or address like %?2%")
	List<User2> getUserByCondition(String name, String address);

	@Query("from User2 where username like %:name%")
	List<User2> getUserByUsername(@Param("name") String name);

	@Query(value = "select * from user2 where username like %:name%", nativeQuery = true)
	List<User2> getUsersByUsername(@Param("name") String username);
}
