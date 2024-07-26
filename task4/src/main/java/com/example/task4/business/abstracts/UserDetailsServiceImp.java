package com.example.task4.business.abstracts;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.task4.business.requests.UserDto;
import com.example.task4.business.responses.GetAllUserResponse;
import com.example.task4.business.responses.GetByIdUserResponse;
import com.example.task4.model.User;

public interface UserDetailsServiceImp extends UserDetailsService {
	public void CreateUser(UserDto userDto);

	public void updateUser(long id, UserDto userDto);

	public void deleteUser(long id);

	List<GetAllUserResponse> getAll();

	GetByIdUserResponse getById(long id);
	
	public User convertDtoToUser(UserDto userDto);
}
