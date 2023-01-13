package com.multi.filter.controller;

import java.util.ArrayList;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multi.filter.dto.ErrorResponseDto;
import com.multi.filter.dto.IRoleDto;
import com.multi.filter.dto.ListResponseDto;
import com.multi.filter.dto.RoleDto;
import com.multi.filter.dto.SuccessResponseDto;
import com.multi.filter.entity.RoleEntity;
import com.multi.filter.service.RoleService;


@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping()
	public ResponseEntity<?> addRole(@RequestBody RoleDto roleDto) {

		roleService.addRole(roleDto);

		return new ResponseEntity<>(new SuccessResponseDto("Role Added", "RoleAdded", roleDto), HttpStatus.CREATED);

	}

	@GetMapping()
	public ResponseEntity<List<RoleDto>> getAllRoles(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String size) {

		Page<IRoleDto> role = roleService.getAllRoles(search, pageNo, size);
		System.out.println("Roles>>" + role);
		if (role.getTotalElements() != 0) {
			return new ResponseEntity(new SuccessResponseDto("Success", "success",
					new ListResponseDto(role.getContent(), role.getTotalElements())), HttpStatus.OK);
		}
		return new ResponseEntity(new ErrorResponseDto("Data Not Found", "dataNotFound"), HttpStatus.NOT_FOUND);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateRole(@Valid @RequestBody RoleEntity role, @PathVariable Long id) {

		this.roleService.updateRole(role, id);

		return new ResponseEntity<>(new SuccessResponseDto("Success", "Role updated successfully", null),
				HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRoles(@PathVariable Long id) {
		roleService.deleteRoles(id);
		return new ResponseEntity<>(new SuccessResponseDto("Success", "Role Deleted successfully", null),
				HttpStatus.OK);
	}

	
}
