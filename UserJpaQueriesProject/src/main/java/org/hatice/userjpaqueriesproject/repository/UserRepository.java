package org.hatice.userjpaqueriesproject.repository;

import org.hatice.userjpaqueriesproject.entity.User;
import org.hatice.userjpaqueriesproject.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	
	User findByFirstNameAndLastName(String firstName, String lastName);
	
	List<User> findByAgeGreaterThan(int age);
	
	List<User> findByStatusOrderByFirstNameAsc(Status status);
	
	@Query("SELECT u FROM User u WHERE u.status = :status")
	List<User> findUsersByStatus(@Param("status") String status);
	
	@Query("SELECT u FROM User u WHERE u.firstName = :firstName AND u.lastName = :lastName")
	User findByFullName(@Param("firstName") String firstName, @Param("lastName") String lastName);
	
	@Query("SELECT u FROM User u ORDER BY u.age DESC")
	List<User> findAllUsersSortedByAge();
	
	@Modifying
	@Query("UPDATE User u SET u.status = :status WHERE u.id = :id")
	void updateUserStatus(@Param("id") Long id, @Param("status") String status);
	
	@Modifying
	@Query("DELETE FROM User u WHERE u.age < :age")
	void deleteUsersYoungerThan(@Param("age") int age);
	
	@Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
	User findByEmailNative(@Param("email") String email);
	
	@Query(value = "SELECT * FROM users WHERE age > :age", nativeQuery = true)
	List<User> findUsersOlderThanNative(@Param("age") int age);
	
	@Query("SELECT u FROM User u WHERE u.age BETWEEN :startAge AND :endAge")
	List<User> findUsersByAgeRange(@Param("startAge") int startAge, @Param("endAge") int endAge);
	
	@Query(value = "SELECT * FROM tbl_user WHERE first_name ILIKE %:keyword%", nativeQuery = true)
	List<User> searchUserByFirstName(@Param("keyword") String keyword);
	
	
	@Query("SELECT COUNT(u) FROM User u")
	long countTotalUsers();
	
	@Query("SELECT u.status,COUNT(u) FROM User u GROUP BY u.status")
	List<Object[]> countUserByStatus();
	
	@Query("select  u FROM User u join FETCH u.addresses WHERE u.id = :userId")
	User findUserWithAddresses(@Param("userId") Long userId);
}