package com.desafio.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.desafio.model.HistoricoModel;

public interface HistoricoRepository extends Repository<HistoricoModel, Integer> {
	void save(HistoricoModel secao);

	void delete(HistoricoModel secao);

	List<HistoricoModel> findAll();
	
	List<HistoricoModel> findByEventoByOrderByDataHora(String evento);
	
	//List<HistoricoModel> findByEventoByOrderByDataHoraDesc(String evento);
	
	//List<HistoricoModel> findByEventoByOrderByIdSecao(String evento);
	
	//List<HistoricoModel> findByEventoByOrderByIdSecaoDesc(String evento);
	
	//List<HistoricoModel> findByEventoIdTipoBebida(String evento, Integer idTipoBebida);
	
	//List<HistoricoModel> findByEventoByIdSecaoByOrderByDataHora(String evento, Integer idSecao);

	List<HistoricoModel> findByResponsavel(String responsavel);
	
	List<HistoricoModel> findByIdSecao(Integer idSecao);
	
	List<HistoricoModel> findByIdTipoBebida(Integer idTipoBebida);
}
