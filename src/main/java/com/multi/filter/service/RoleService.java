package com.multi.filter.service;

import org.springframework.data.domain.Page;
import com.multi.filter.dto.IRoleDto;
import com.multi.filter.dto.RoleDto;
import com.multi.filter.entity.RoleEntity;


public interface RoleService {

	public void addRole(RoleDto roleDto);

	Page<IRoleDto> getAllRoles(String search, String from, String to);

	RoleEntity updateRole(RoleEntity role, Long id);

	void deleteRoles(Long id);

}
