package com.prishita.jobportal.resources;

import com.prishita.jobportal.entity.Applicants;
import com.prishita.jobportal.entity.Jobs;
import com.prishita.jobportal.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/job")
public class JobResource {

	@Autowired
	private JobService jobService;

	@PreAuthorize("hasAnyAuthority('EMPLOYER')")
	@PostMapping("")
	public Jobs createJob(@RequestBody Jobs job,
	                      @RequestParam("category") String category,
	                      Principal principal) {
		return jobService.createJob(job, principal, category);
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@PutMapping("/{id}/verify/{action}")
	public Jobs verifyJob(@PathVariable("id") Long id,
	                      @PathVariable("action") String action) {
		return jobService.verifyJob(id,action);
	}

	@PreAuthorize("hasAnyAuthority('EMPLOYEE')")
	@PostMapping("/{id}/apply")
	public Applicants applyJob(@PathVariable("id") Long id,
	                           Principal principal) {
		return jobService.applyJob(id, principal.getName());
	}


}
