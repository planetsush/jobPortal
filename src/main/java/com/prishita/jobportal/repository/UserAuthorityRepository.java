package com.prishita.jobportal.repository;

import com.prishita.jobportal.entity.User;
import com.prishita.jobportal.entity.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {

	List<UserAuthority> getAllByUser(User user);
}
