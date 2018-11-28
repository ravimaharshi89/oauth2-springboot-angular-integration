package com.oauth.angular.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.oauth.angular.repository.AccountRepository;

public class AccountUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return accountRepository
                .findByUsername(username)
                .map(account -> new User(account.getUsername(), account.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER")))
                .orElseThrow(() -> new UsernameNotFoundException("Could not find " + username));
	}

}
