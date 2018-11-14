package com.desafio.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.desafio.model.SecaoModel;

public interface SecaoRepository extends Repository<SecaoModel, Integer> {
	void save(SecaoModel secao);
	 
	void delete(SecaoModel secao);
 
	List<SecaoModel> findAll();
 
	SecaoModel findById(Integer id);
	
	SecaoModel findByNome(String nome);
}
