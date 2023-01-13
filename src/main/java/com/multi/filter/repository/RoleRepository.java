package com.multi.filter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.filter.dto.IRoleDto;
import com.multi.filter.entity.RoleEntity;


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	Page<IRoleDto> findByOrderById(Pageable paging, Class<IRoleDto> class1);

	Page<IRoleDto> findByRoleNameContainingIgnoreCaseOrderById(String search, Pageable paging, Class<IRoleDto> class1);

	RoleEntity findByRoleNameContainingIgnoreCase(String roleName);

	RoleEntity findByroleNameContainingIgnoreCase(String string);

}
