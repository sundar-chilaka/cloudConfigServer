package com.sundar.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sundar.model.UserInfo;
import com.sundar.pojo.LoginRequst;
import com.sundar.repository.UserInfoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String addUser(LoginRequst loginRequst) {
		List<LoginRequst> userList = userInfoRepository.findAll();
		Boolean flage = false; // Initialize flage to false

		if (userList != null && !userList.isEmpty()) {
			for (LoginRequst user : userList) {
				if (user.getUsername().equals(loginRequst.getUsername())) {
					flage = true; // User already exists
					break; // Exit loop early
				}
			}
		}

		if (flage) {
			log.info("User already exists.");
			return "User already exists.";
		} else {
			loginRequst.setPassword(passwordEncoder.encode(loginRequst.getPassword()));
			userInfoRepository.save(loginRequst);
			return "User saved";
		}
	}
}
