package com.multi.filter.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multi.filter.dto.IUserDto;
import com.multi.filter.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	Page<IUserDto> findByOrderById(Pageable paging, Class<IUserDto> class1);

	Page<IUserDto> findByNameContainingIgnoreCaseOrderById(String search, Pageable paging, Class<IUserDto> class1);

	Optional<UserEntity> findByEmailContainingIgnoreCase(String email);
	
	@Query(value = "SELECT u.id as userId, u.name as name ,r.id as roleId ,r.role_name as roleName from users u\r\n"
			+ "join user_role ur on ur.user_id=u.id\r\n"
			+ "join roles r on r.id=ur.role_id where u.is_active = true AND (u.address ILIKE %:search1% OR u.email ILIKE %:search1% OR r.role_name ILIKE %:search1%)"
			+ "and (:id = '' OR u.id IN (select unnest(cast(string_to_array(:id, ',') as bigint[]))))",nativeQuery = true)
	Page<IUserDto> findByOrderByIdDesc(@Param("search1") String search1,@Param("id") String id,
			@Param("search") String search,
			Pageable pageable, Class<IUserDto> class1);

	UserEntity findByEmail(String email);

	


}
