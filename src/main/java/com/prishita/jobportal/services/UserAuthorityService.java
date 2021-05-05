package com.prishita.jobportal.services;

import com.prishita.jobportal.entity.User;
import com.prishita.jobportal.entity.UserAuthority;
import com.prishita.jobportal.repository.UserAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserAuthorityService {

	@Autowired
	public UserAuthorityRepository userAuthorityRepository;

	public List<GrantedAuthority> getAllByUser(User user) {

		List<UserAuthority> authorities = userAuthorityRepository.getAllByUser(user);
		List<GrantedAuthority> result = new LinkedList<>();
		for (UserAuthority authority: authorities) {
			result.add(new SimpleGrantedAuthority(authority.getAuthorities().getAuthority()));
		}
		return  result;
	}
}
