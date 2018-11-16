package com.desafio.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.desafio.model.HistoricoModel;

public interface HistoricoRepository extends Repository<HistoricoModel, Integer> {
	void save(HistoricoModel secao);

	void delete(HistoricoModel secao);

	List<HistoricoModel> findAll();
	
	List<HistoricoModel> findByEvento(String evento);
	
	List<HistoricoModel> findByEventoOrderByDataHora(String evento);
	
	List<HistoricoModel> findByEventoOrderByDataHoraDesc(String evento);
	
	List<HistoricoModel> findByEventoOrderByIdSecao(String evento);
	
	List<HistoricoModel> findByEventoOrderByIdSecaoDesc(String evento);
	
	List<HistoricoModel> findByEventoAndIdTipoBebida(String evento, Integer idTipoBebida);
	
	List<HistoricoModel> findByEventoAndIdSecao(String evento, Integer idSecao);

	List<HistoricoModel> findByEventoAndIdSecaoOrderByDataHora(String evento, Integer idSecao);

	List<HistoricoModel> findByResponsavel(String responsavel);
	
	List<HistoricoModel> findByIdSecao(Integer idSecao);
	
	List<HistoricoModel> findByIdTipoBebida(Integer idTipoBebida);
}
