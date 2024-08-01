package com.eventTracking.Task5.business.concretes;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eventTracking.Task5.business.abstracts.UserDetailsServiceImp;
import com.eventTracking.Task5.business.dto.UserDto;
import com.eventTracking.Task5.business.responses.GetAllUserResponse;
import com.eventTracking.Task5.business.responses.GetByIdUserResponse;
import com.eventTracking.Task5.model.User;
import com.eventTracking.Task5.model.UserDetailsImp;
import com.eventTracking.Task5.repository.UserRepository;


@Service
public class UserDetailsManager implements UserDetailsServiceImp {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User convertDtoToUser(UserDto userDto) {
	    User user = new User();
	    user.setUsername(userDto.getUsername());
	    user.setEmail(userDto.getEmail());
	    user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Encode password
	    user.setRoles(userDto.getRoles());
	    return user;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		return user.map(UserDetailsImp::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
	}
	
	@Override
    public void CreateUser(UserDto userDto) {
        User user = convertDtoToUser(userDto);
        userRepository.save(user);
    }
	
	@Override
	public void updateUser(long id, UserDto userDto) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + id));
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userRepository.save(user);
	}

	 @Override
	    public void deleteUser(long id) {
	        if (userRepository.existsById(id)) {
	            userRepository.deleteById(id);
	        } else {
	            throw new UsernameNotFoundException("User not found with ID: " + id);
	        }
	    }

	@Override
	public List<GetAllUserResponse> getAll() {
		List<User> users = userRepository.findAll();
		// RETURN edilecek değer
		List<GetAllUserResponse> usersResponses = new ArrayList<GetAllUserResponse>();

		for (User user : users) {
			GetAllUserResponse responseItem = new GetAllUserResponse();
			responseItem.setId(user.getId());
			responseItem.setName(user.getUsername());
			responseItem.setEmail(user.getEmail());			
			usersResponses.add(responseItem);
		}
		return usersResponses;
	}

	@Override
	public GetByIdUserResponse getById(long id) {
		User user = userRepository.findById(id).orElseThrow();
		
		GetByIdUserResponse response = new GetByIdUserResponse();
	    response.setId(user.getId());
	    response.setName(user.getUsername());
	    response.setEmail(user.getEmail());
	    
	    return response;
	}
	

}
