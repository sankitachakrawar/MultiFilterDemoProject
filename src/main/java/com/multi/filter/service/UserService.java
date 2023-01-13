package com.multi.filter.service;

import org.springframework.data.domain.Page;

import com.multi.filter.dto.IUserDto;
import com.multi.filter.dto.UserDto;
import com.multi.filter.entity.UserEntity;


public interface UserService {

	public void addUsers(UserDto userDto);

	UserEntity updateUser(UserEntity user, Long id);

	void deleteUser(Long id);

	Page<IUserDto> getAllUsers(String search,String from, String to,String search1,String id);
}
