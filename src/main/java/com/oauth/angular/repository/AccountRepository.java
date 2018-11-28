package com.oauth.angular.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oauth.angular.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
 	Optional<Account> findByUsername(String username);
}
