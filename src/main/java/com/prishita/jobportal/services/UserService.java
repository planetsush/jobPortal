package com.prishita.jobportal.services;

import com.prishita.jobportal.UserPayload;
import com.prishita.jobportal.entity.*;
import com.prishita.jobportal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
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

	@Autowired
	private EmployersRepository employersRepository;

	@Autowired
	private EmployeesRepository employeesRepository;

	@Autowired
	private ResumesRepository resumesRepository;


	@Transactional
	public User createUser(UserPayload userPayload, String role) {
		User user = new User();
		Optional<Authorities> authority = authoritiesRepository.findAuthoritiesByAuthority(role);
		if (authority.isEmpty()) {
			return null;
		}
		user.setAccountId(UUID.randomUUID().toString());
		user.setPassword(passwordEncoder.encode(userPayload.getPassword()));
		user.setEmail(userPayload.getEmail());
		user.setPhno(userPayload.getPhoneNo());
		user.setName(userPayload.getName());
		user.setActive(true);
		user.setAddress(userPayload.getAddress());
		user = userRepository.save(user);

		if (role.equals("EMPLOYER")) {
			createEmployer(userPayload, user);
		} else if (role.equals("EMPLOYEE")) {
			createEmployee(userPayload, user);
		}

		UserAuthority userAuthority = new UserAuthority();
		userAuthority.setUser(user);
		userAuthority.setAuthorities(authority.get());
		userAuthorityRepository.save(userAuthority);

		return user;
	}

	private void createEmployee(UserPayload userPayload, User user) {
		Employees employee = new Employees();
		employee.setUser(user);
		employee.setAnnualSalary(userPayload.getAnnualSalary());
		employee.setGender(userPayload.getGender());
		employee.setCurrentIndustry(userPayload.getCurrentIndustry());
		employee.setProfile(userPayload.getProfile());
		employee.setResume(resumesRepository.getOne(userPayload.getResume()));
		employee.setQuali(userPayload.getQuali());
		employee.setCurrentLocation(userPayload.getCurrentLocation());
		employeesRepository.save(employee);

	}

	public Long saveResume(MultipartFile resumePayload) {
		if(resumePayload == null) {
			return null;
		}
		try {
			Resumes resume = new Resumes();
			resume.setPdf(resumePayload.getBytes());
			return resumesRepository.save(resume).getId();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void createEmployer(UserPayload userPayload, User user) {
		Employers employer = new Employers();
		employer.setUser(user);
		employer.setCompany(userPayload.getCompany());
		employer.setProfile(userPayload.getProfile());
		employersRepository.save(employer);
	}

	public Optional<User> getUser(Principal principal) {
		System.out.println(principal.getName());
		return userRepository.findUserByEmail(principal.getName());
	}
}
