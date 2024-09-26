package com.sundar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sundar.model.UserInfo;
import com.sundar.pojo.LoginRequst;
import com.sundar.service.UserInfoService;

@RestController
@RequestMapping("/userInfo")
@CrossOrigin
public class UserInfoComtroller {

	@Autowired
	UserInfoService infoService;
	
	@PostMapping("/new")
	public ResponseEntity<String> addNewUser(@RequestBody LoginRequst loginRequst) {
		System.out.println("-----------------");
		return new ResponseEntity<>(infoService.addUser(loginRequst), HttpStatus.CREATED);
	}
}
