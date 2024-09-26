package com.sundar.service;

import java.util.List;

import com.sundar.exception.ProdectNotFoundException;
import com.sundar.model.Prodect;

public interface ProdectService {

	public Prodect saveProdect(Prodect prodect);
	
	public List<Prodect>fetchAllProducts();
	
	public Prodect fetchProdect(Long prodectId) throws ProdectNotFoundException;
	
	
}
