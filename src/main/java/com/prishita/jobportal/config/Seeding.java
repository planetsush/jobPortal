package com.prishita.jobportal.config;

import com.prishita.jobportal.repository.AuthoritiesRepository;
import com.prishita.jobportal.services.AuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeding implements CommandLineRunner {

	@Value("${seed.enabled}")
	public Boolean seed;

	@Autowired
	public AuthoritiesService authoritiesService;


	@Override
	public void run(String... args) throws Exception {
		if (null != seed && seed) {
			createAuthorities();
		}
	}

	private void createAuthorities() {
		authoritiesService.createAuthority("ADMIN");
		authoritiesService.createAuthority("EMPLOYEE");
		authoritiesService.createAuthority("EMPLOYER");
	}
}
