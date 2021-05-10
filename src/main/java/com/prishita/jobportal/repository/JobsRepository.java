package com.prishita.jobportal.repository;

import com.prishita.jobportal.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobsRepository extends JpaRepository<Jobs, Long> {
}
