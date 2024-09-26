package com.sundar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sundar.JWT.JwtUtils;
import com.sundar.exception.ProdectNotFoundException;
import com.sundar.model.Prodect;
import com.sundar.pojo.LoginRequst;
import com.sundar.pojo.LoginResponce;
import com.sundar.service.ProdectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/prodect")
public class ProdectController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	ProdectService prodectService;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Spring Security";
	}

	@PostMapping("/save")
	public ResponseEntity<Prodect> saveProdecct(@Valid @RequestBody Prodect prodect) {
		return new ResponseEntity<>(prodectService.saveProdect(prodect), HttpStatus.CREATED);
	}

	@GetMapping("/fetchAll")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<Prodect>> fetchAllProdect() {
		return new ResponseEntity<>(prodectService.fetchAllProducts(), HttpStatus.OK);
	}

	@GetMapping("/{prodectId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Prodect fetchProdect(@PathVariable Long prodectId) throws ProdectNotFoundException {
		return prodectService.fetchProdect(prodectId);
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequst loginRequst) {
		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequst.getUsername(), loginRequst.getPassword()));
		} catch (AuthenticationException e) {

			Map<String, Object> map = new HashMap<>();
			map.put("message", "Bad credentials");
			map.put("status", false);
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
			// TODO: handle exception
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		String jwtToken = jwtUtils.generateTokenFromUserName(userDetails);

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		LoginResponce responce = new LoginResponce(userDetails.getUsername(), jwtToken, roles);
		return ResponseEntity.ok(responce);
	}
}
