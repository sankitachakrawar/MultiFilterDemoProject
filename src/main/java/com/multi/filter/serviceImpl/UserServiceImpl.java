package com.multi.filter.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.multi.filter.dto.IUserDto;
import com.multi.filter.dto.UserDto;
import com.multi.filter.entity.UserEntity;
import com.multi.filter.exception.ResourceNotFoundException;
import com.multi.filter.repository.UserRepository;
import com.multi.filter.service.UserService;
import com.multi.filter.utils.PaginationUsingFromTo;



@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void addUsers(UserDto userDto) {
	
		UserEntity entity=new UserEntity();
		entity.setName(userDto.getName());
		entity.setUsername(userDto.getUsername());
		entity.setEmail(userDto.getEmail());
		entity.setAddress(userDto.getAddress());
		entity.setPassword(userDto.getPassword());
		
		UserEntity userEntity  = userRepository.save(entity);
		
		
	}

	@Override
	public Page<IUserDto> getAllUsers(String search,String from, String to,String search1,String id) {
		
		Pageable paging = new PaginationUsingFromTo().getPagination(from, to);
		if ((search == "") || (search == null) || (search.length() == 0)) {
		
		return userRepository.findByOrderByIdDesc(search1,id,search,paging,IUserDto.class);
		}else {

			return userRepository.findByNameContainingIgnoreCaseOrderById(search, paging, IUserDto.class);

		}
	}

	@Override
	public UserEntity updateUser(UserEntity user, Long id) {
		UserEntity userEntity = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("users", "id", id));
		userEntity.setName(user.getName());
		userEntity.setEmail(user.getEmail());
		userEntity.setAddress(user.getAddress());
		userEntity.setPassword(user.getPassword());
		userEntity.setUsername(user.getUsername());
		UserEntity entity = this.userRepository.save(userEntity);
		return entity;
	}

	@Override
	public void deleteUser(Long id) {
		this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("users", "id", id));
		this.userRepository.deleteById(id);
	}

}
