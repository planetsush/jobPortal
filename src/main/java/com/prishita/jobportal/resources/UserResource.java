package com.prishita.jobportal.resources;

import com.prishita.jobportal.UserPayload;
import com.prishita.jobportal.entity.User;
import com.prishita.jobportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping("/me")
	public User loggedInUser(Principal principal) {
		Optional<User> user = userService.getUser(principal);
		return user.orElse(null);
	}

	@PostMapping("/signUp")
	public User createUser(@RequestBody UserPayload user,
	                       @RequestParam("role") String role,
	                       Error error) {
		return userService.createUser(user, role);
	}

	@PostMapping(value = "/resume", consumes = {"multipart/form-data", "application/json"})
	public Long uploadResume(@RequestParam(value = "resume", required = false) MultipartFile resume) {
		if (resume== null) {
			return 0L;
		}
		return userService.saveResume(resume);
	}
}
