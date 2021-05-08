package com.prishita.jobportal.services;

import com.prishita.jobportal.entity.Authorities;
import com.prishita.jobportal.repository.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesService {

	@Autowired
	private AuthoritiesRepository authoritiesRepository;

	public void createAuthority(String admin) {
		Authorities authority = new Authorities(admin);
		authoritiesRepository.save(authority);
	}
}
