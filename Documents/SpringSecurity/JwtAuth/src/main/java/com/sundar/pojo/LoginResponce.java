package com.sundar.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponce {
	
	private String jwtToken;
	
	private String username;
	private List<String> roles;

}
