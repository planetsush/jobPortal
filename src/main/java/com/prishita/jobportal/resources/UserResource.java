package com.prishita.jobportal.resources;

import com.prishita.jobportal.entity.User;
import com.prishita.jobportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping("/me")
	public User loggedInUser(Principal principal){
		Optional<User> user = userService.getUser(principal);
		return user.orElse(null);
	}

	@PostMapping("")
	public User createUser(@RequestBody User user,
	                       @RequestParam("role") String role) {
		return userService.createUser(user, role);
	}
}
