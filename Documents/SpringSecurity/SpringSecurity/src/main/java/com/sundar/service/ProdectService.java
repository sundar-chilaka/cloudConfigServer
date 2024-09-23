package com.sundar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sundar.model.Prodect;
import com.sundar.repository.ProdectRepository;
@Service
public class ProdectService {

	@Autowired
	ProdectRepository prodectRepository;
	
	public Prodect saveProdect(Prodect prodect) {
		return prodectRepository.save(prodect);
	}

	public List<Prodect> getAllProdects() {
		return prodectRepository.findAll();
	}

	public Optional<Prodect> getProdectById(Long prodectId) {
		return prodectRepository.findById(prodectId);
	}

}
