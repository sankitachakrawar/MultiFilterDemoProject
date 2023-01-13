package com.multi.filter.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.multi.filter.dto.IRoleDto;
import com.multi.filter.dto.RoleDto;
import com.multi.filter.entity.RoleEntity;
import com.multi.filter.exception.ResourceNotFoundException;
import com.multi.filter.repository.RoleRepository;
import com.multi.filter.service.RoleService;
import com.multi.filter.utils.PaginationUsingFromTo;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void addRole(RoleDto roleDto) {

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRoleName(roleDto.getRoleName());
		roleEntity.setDescription(roleDto.getDescription());
		roleRepository.save(roleEntity);

	}

	@Override
	public Page<IRoleDto> getAllRoles(String search, String from, String to) {
		Pageable paging = new PaginationUsingFromTo().getPagination(from, to);
		if ((search == "") || (search == null) || (search.length() == 0)) {

			return roleRepository.findByOrderById(paging, IRoleDto.class);
		} else {

			return roleRepository.findByRoleNameContainingIgnoreCaseOrderById(search, paging, IRoleDto.class);

		}
	}

	@Override
	public RoleEntity updateRole(RoleEntity role, Long id) {
		RoleEntity roleEntity = this.roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("role", "id", id));
		roleEntity.setRoleName(role.getRoleName());
		roleEntity.setDescription(role.getDescription());
		RoleEntity entity = roleRepository.save(roleEntity);
		return entity;
	}

	@Override
	public void deleteRoles(Long id) {

		this.roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("role", "id", id));

		this.roleRepository.deleteById(id);
	}

	


}
