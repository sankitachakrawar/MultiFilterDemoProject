package com.multi.filter.serviceImpl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.filter.dto.AssignRole;
import com.multi.filter.entity.RoleEntity;
import com.multi.filter.entity.UserEntity;
import com.multi.filter.entity.UserRoleEntity;
import com.multi.filter.entity.UserRoleId;
import com.multi.filter.repository.RoleRepository;
import com.multi.filter.repository.UserRepository;
import com.multi.filter.repository.UserRoleRepository;
import com.multi.filter.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public void addRoleToUser(AssignRole assignRole) {

		try {
			ArrayList<UserRoleEntity> roles = new ArrayList<>();

			UserEntity user = userRepository.findByEmail(assignRole.getEmail());

			RoleEntity role = roleRepository.findByRoleNameContainingIgnoreCase(assignRole.getRoleName());

			UserRoleEntity userRoleEntity = new UserRoleEntity();

			UserRoleId userRoleId = new UserRoleId(user, role);

			userRoleEntity.setPk(userRoleId);

			roles.add(userRoleEntity);

			userRoleRepository.saveAll(roles);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
