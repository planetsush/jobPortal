package com.prishita.jobportal.repository;

import com.prishita.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findUserByAccountId(String accountId);

	Optional<User> findUserByUsername(String username);
}
