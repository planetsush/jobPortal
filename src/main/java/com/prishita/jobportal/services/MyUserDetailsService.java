package com.prishita.jobportal.services;

import com.prishita.jobportal.CustomUserDetails;
import com.prishita.jobportal.entity.User;
import com.prishita.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepository.findUserByUsername(username);
		userOptional.orElseThrow(() -> new UsernameNotFoundException("User no found "+ username));
		return userOptional.map(CustomUserDetails::new).get();
	}
}