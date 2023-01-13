package com.multi.filter.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.filter.dto.AssignRole;
import com.multi.filter.dto.ErrorResponseDto;
import com.multi.filter.dto.SuccessResponseDto;


@RestController
@RequestMapping("/userRole")
public class UserRoleController {

	@Autowired
	private com.multi.filter.service.UserRoleService userRoleService;
	
	@PostMapping()
	public ResponseEntity<?> assignRoleToUser(@RequestBody AssignRole assignRole, HttpServletRequest request) {

		try {
			userRoleService.addRoleToUser(assignRole);
			return new ResponseEntity<>(new SuccessResponseDto("Role assign to user", "Roleassigntouser", null),
					HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto("Role not assign to user", "Rolenotassigntouser"),
					HttpStatus.BAD_REQUEST);
		}
	}

}
