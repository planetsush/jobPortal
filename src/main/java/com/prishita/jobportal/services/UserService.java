package com.prishita.jobportal.services;

import com.prishita.jobportal.entity.User;
import com.prishita.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;


	public User createUser(User user) {
		user.setAccountId(UUID.randomUUID().toString());
		if (user.getRoles() == null) {
			user.setRoles("EMPLOYEE");
		}
		user.setActive(true);
		return userRepository.save(user);
	}

	public Optional<User> getUser(Principal principal) {
		return userRepository.findUserByAccountId(principal.getName());
	}
}
