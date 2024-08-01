package com.eventTracking.Task5.business.abstracts;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.eventTracking.Task5.business.dto.UserDto;
import com.eventTracking.Task5.business.responses.GetAllUserResponse;
import com.eventTracking.Task5.business.responses.GetByIdUserResponse;
import com.eventTracking.Task5.model.User;



public interface UserDetailsServiceImp extends UserDetailsService {
	public void CreateUser(UserDto userDto);

	public void updateUser(long id, UserDto userDto);

	public void deleteUser(long id);

	List<GetAllUserResponse> getAll();

	GetByIdUserResponse getById(long id);
	
	public User convertDtoToUser(UserDto userDto);
}
