package com.sundar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sundar.model.Prodect;
@Repository
public interface ProdectRepository extends JpaRepository<Prodect, Long>{

}
