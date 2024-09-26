package com.sundar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sundar.exception.ProdectNotFoundException;
import com.sundar.model.Prodect;
import com.sundar.repository.ProdectRepository;

@Service
public class ProdectServiceImpl implements ProdectService {

	@Autowired
	ProdectRepository prodectRepository;
	boolean flag;

	@Override
	public Prodect saveProdect(Prodect prodect) {
		// TODO Auto-generated method stub
		return prodectRepository.save(prodect);
	}

	@Override
	public List<Prodect> fetchAllProducts() {
		// TODO Auto-generated method stub
		return prodectRepository.findAll();
	}

	@Override
	public Prodect fetchProdect(Long prodectId) throws ProdectNotFoundException {
		Prodect prodect = null;
		if (prodectId != null && prodectId != 0) {
			flag = prodectRepository.existsById(prodectId);
		}
		if (flag) {
			prodect = prodectRepository.findById(prodectId).get();
		} else {
			throw new ProdectNotFoundException("Prodect Not Found");
		}
		// TODO Auto-generated method stub
		return prodect;
	}

}
