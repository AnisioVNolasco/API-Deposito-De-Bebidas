package com.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.model.HistoricoModel;
import com.desafio.repository.HistoricoRepository;

@RestController
@RequestMapping("/historico")
public class HistoricoService {
	
	@Autowired
	private HistoricoRepository historicoRepository;
	
	/**
	 * CONSULTAR HISTORICO DE ENTRADA
	 * 
	 * @return
	 */
	@RequestMapping(value = "/historico/entrada", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<HistoricoModel> consultar() {

		return this.historicoRepository.findAllByOrderByDataHora();
	}

}
