package com.oauth.angular.repo.db1;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oauth.angular.entity.db1.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
 	Optional<Account> findByUsername(String username);
}
