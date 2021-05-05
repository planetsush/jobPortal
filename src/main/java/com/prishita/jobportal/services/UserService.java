package com.prishita.jobportal.services;

import com.prishita.jobportal.entity.Authorities;
import com.prishita.jobportal.entity.User;
import com.prishita.jobportal.entity.UserAuthority;
import com.prishita.jobportal.repository.AuthoritiesRepository;
import com.prishita.jobportal.repository.UserAuthorityRepository;
import com.prishita.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserAuthorityRepository userAuthorityRepository;

	@Autowired
	private AuthoritiesRepository authoritiesRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Transactional
	public User createUser(User user, String role) {
		Optional<Authorities> authority = authoritiesRepository.findAuthoritiesByAuthority(role);
		if(authority.isEmpty()) {
			return null;
		}

		user.setAccountId(UUID.randomUUID().toString());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setActive(true);
		user = userRepository.save(user);

		UserAuthority userAuthority = new UserAuthority();
		userAuthority.setUser(user);
		userAuthority.setAuthorities(authority.get());
		userAuthorityRepository.save(userAuthority);

		return user;
	}

	public Optional<User> getUser(Principal principal) {
		System.out.println(principal.getName());
		return userRepository.findUserByUsername(principal.getName());
	}
}
