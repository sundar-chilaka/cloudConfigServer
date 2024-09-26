package com.sundar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sundar.model.UserInfo;
import com.sundar.repository.UserInfoRepository;

public class UserInfoUserDetailsService implements UserDetailsService{
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Optional<UserInfo> userInfo = userInfoRepository.findByUname(username);
	    return userInfo.map(UserInfoUserDetails::new)
	            .orElseThrow();
	}



}
