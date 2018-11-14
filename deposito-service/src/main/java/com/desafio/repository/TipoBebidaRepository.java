package com.desafio.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.desafio.model.TipoBebidaModel;

public interface TipoBebidaRepository extends Repository<TipoBebidaModel, Integer> {
	
	void save(TipoBebidaModel tipoBebida);
	 
	void delete(TipoBebidaModel tipoBebida);
 
	List<TipoBebidaModel> findAll();
 
	TipoBebidaModel findById(Integer id);
	
	TipoBebidaModel findByNome(String nome);
}
