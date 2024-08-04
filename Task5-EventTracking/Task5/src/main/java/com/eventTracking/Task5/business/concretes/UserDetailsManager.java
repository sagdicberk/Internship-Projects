package com.eventTracking.Task5.business.concretes;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eventTracking.Task5.business.abstracts.UserDetailsServiceImp;
import com.eventTracking.Task5.business.dto.UserDto;
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return new UserDetailsImp(user);
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
	public List<User> getAll() {
		List<User> users = userRepository.findAll();
		// RETURN edilecek değer
		List<User> usersResponses = new ArrayList<User>();

		for (User user : users) {
			User responseItem = new User();
			responseItem.setId(user.getId());
			responseItem.setUsername(user.getUsername());
			responseItem.setEmail(user.getEmail());			
			usersResponses.add(responseItem);
		}
		return usersResponses;
	}

	@Override
	public User getById(long id) {
		User user = userRepository.findById(id).orElseThrow();
		
		User response = new User();
	    response.setId(user.getId());
	    response.setUsername(user.getUsername());
	    response.setEmail(user.getEmail());
	    
	    return response;
	}

	public long getUserIdByEmail(String email) {
	    User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
	    return user.getId();
	}


	public boolean isAdmin(long userId) {
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));
	    
	    // Assuming roles are stored in a List<String>
	    String roles = user.getRoles(); // Adjust the method to get roles if needed
	    if (roles == null) {
	        return false;
	    }
	    return roles.contains("ADMIN");
	}


}
