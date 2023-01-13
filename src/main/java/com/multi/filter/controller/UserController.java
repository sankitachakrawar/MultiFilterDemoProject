package com.multi.filter.controller;

import java.util.List;
import java.util.Optional;

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
import com.multi.filter.dto.IUserDto;
import com.multi.filter.dto.ListResponseDto;
import com.multi.filter.dto.SuccessResponseDto;
import com.multi.filter.dto.UserDto;
import com.multi.filter.entity.UserEntity;
import com.multi.filter.repository.UserRepository;
import com.multi.filter.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {

		try {
			String email = userDto.getEmail();
			Optional<UserEntity> databaseEmail = userRepository.findByEmailContainingIgnoreCase(email);
			if ((databaseEmail == null) || databaseEmail.isEmpty()) {
				userService.addUsers(userDto);

				return new ResponseEntity<>(new SuccessResponseDto("User Registered", "UserRegistered", null),
						HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(
						new ErrorResponseDto("User Email Id Already Exist", "UserEmailIdAlreadyExist"),
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorResponseDto("User Not Registered", "UserNotRegistered"),
					HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping()
	public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String size,
			@RequestParam(defaultValue = "") String search1,
			@RequestParam(defaultValue = "")String id) {

		Page<IUserDto> user = userService.getAllUsers(search,pageNo,size,search1,id);
		if (user.getTotalElements() != 0) {
			return new ResponseEntity(new SuccessResponseDto("Success", "success",
					new ListResponseDto(user.getContent(), user.getTotalElements())), HttpStatus.OK);
		}
		return new ResponseEntity(new ErrorResponseDto("Data Not Found", "dataNotFound"), HttpStatus.NOT_FOUND);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserEntity user, @PathVariable Long id) {

		this.userService.updateUser(user, id);

		return new ResponseEntity<>(new SuccessResponseDto("Success", "User updated successfully", null),
				HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(new SuccessResponseDto("Success", "User deleted successfully", null),
				HttpStatus.OK);
	}
}
