package com.sundar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sundar.model.Prodect;
import com.sundar.service.ProdectService;

@RestController
@RequestMapping("/prodect")
public class ProdectController {

	@Autowired
	ProdectService prodectService;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Spring Security";
	}
	@PostMapping("/save")
	public Prodect saveProdect(@RequestBody Prodect prodect) {
		System.out.println("-----------------------------------------------------------------------------");
		return prodectService.saveProdect(prodect);
	}
	
	@GetMapping("/fetchAll")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<Prodect>> fetchAllProdect() {
		return new ResponseEntity<>(prodectService.getAllProdects(), HttpStatus.OK);
	}

	@GetMapping("/{prodectId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Optional<Prodect>> fetchProdect(@PathVariable Long prodectId) {
		return new ResponseEntity<>(prodectService.getProdectById(prodectId), HttpStatus.CREATED);
	}
}
