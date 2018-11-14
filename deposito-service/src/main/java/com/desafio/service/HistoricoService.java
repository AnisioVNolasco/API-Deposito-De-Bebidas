package com.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.dto.EstoqueSaidaDTO;
import com.desafio.model.HistoricoModel;
import com.desafio.repository.HistoricoRepository;
import com.desafio.util.BadRequestException;
import com.desafio.util.Evento;

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
	@RequestMapping(value = "/entrada", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<HistoricoModel> consultarEntrada() {

		return this.historicoRepository.findByEventoByOrderByDataHora(Evento.ENTRADA.getEvento());
	}

	/**
	 * CONSULTAR HISTORICO DE SAIDA
	 * 
	 * @return
	 */
	@RequestMapping(value = "/saida", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<HistoricoModel> consultarSaida(@RequestBody EstoqueSaidaDTO saida) {

		return this.historicoRepository.findByEventoByOrderByDataHora(Evento.SAIDA.getEvento());
	}
	
	/**
	 * CONSULTAR HISTORICO DE ENTRADA COM PARAMETROS DE ORDENACAO
	 * 
	 * @return
	 */
	@RequestMapping(value = "/entrada/{parametro}/{ordem}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<HistoricoModel> consultarEntrada(@PathVariable("parametro") String parametro, @PathVariable("ordem") String ordem) {
		if(parametro.equals("data")) {
			if(ordem.equals("desc")) {
				//return this.historicoRepository.findByEventoByOrderByDataHoraDesc(Evento.ENTRADA.getEvento());
			} else if(ordem.equals("asc")) {
				return this.historicoRepository.findByEventoByOrderByDataHora(Evento.ENTRADA.getEvento());
			}			
		} /*else if(parametro.equals("secao")) {
			if(ordem.equals("desc")) {
				return this.historicoRepository.findByEventoByOrderByIdSecaoDesc(Evento.ENTRADA.getEvento());
			} else if(ordem.equals("asc")) {
				return this.historicoRepository.findByEventoByOrderByIdSecao(Evento.ENTRADA.getEvento());
			}
		}*/
		throw new BadRequestException();
	}
	
	
	/**
	 * CONSULTAR HISTORICO DE SAIDA COM PARAMETROS DE ORDENACAO
	 * 
	 * @return
	 */
	@RequestMapping(value = "/saida/{parametro}/{ordem}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<HistoricoModel> consultarSaida(@PathVariable("parametro") String parametro, @PathVariable("ordem") String ordem) {
		if(parametro.equals("data")) {
			if(ordem.equals("desc")) {
				//return this.historicoRepository.findByEventoByOrderByDataHoraDesc(Evento.SAIDA.getEvento());
			} else if(ordem.equals("asc")) {
				return this.historicoRepository.findByEventoByOrderByDataHora(Evento.SAIDA.getEvento());
			}			
		} /*else if(parametro.equals("secao")) {
			if(ordem.equals("desc")) {
				return this.historicoRepository.findByEventoByOrderByIdSecaoDesc(Evento.SAIDA.getEvento());
			} else if(ordem.equals("asc")) {
				return this.historicoRepository.findByEventoByOrderByIdSecao(Evento.SAIDA.getEvento());
			}
		}*/
		throw new BadRequestException();
	}
	
	/**
	 * CONSULTAR HISTORICO DE ENTRADA POR TIPO DE BEBIDA
	 * 
	 * @return
	 */
	@RequestMapping(value = "/entrada/tipobebida={id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<HistoricoModel> consultarEntradaTipoBebida(@PathVariable("id") Integer id) {
		if(id != null) {
			//return this.historicoRepository.findByEventoIdTipoBebida(Evento.ENTRADA.getEvento(), id);
		}
		throw new BadRequestException();
	}
	
	/**
	 * CONSULTAR HISTORICO DE SAIDA POR TIPO DE BEBIDA
	 * 
	 * @return
	 */
	@RequestMapping(value = "/saida/tipobebida={id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<HistoricoModel> consultarSaidaTipoBebida(@PathVariable("id") Integer id) {
		if(id != null) {
			//return this.historicoRepository.findByEventoIdTipoBebida(Evento.SAIDA.getEvento(), id);
		}
		throw new BadRequestException();
	}
	
	/**
	 * CONSULTAR HISTORICO DE SAIDA POR TIPO DE BEBIDA
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{evento}/secao={id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<HistoricoModel> consultarEntradaSaidaSecao(@PathVariable("evento") String evento, @PathVariable("id") Integer id) {
		if(Evento.ENTRADA.getEvento().equals(evento)) {
			if(id != null) {
				//return this.historicoRepository.findByEventoIdTipoBebida(Evento.ENTRADA.getEvento(), id);
			}
		} else if(Evento.SAIDA.getEvento().equals(evento)) {
			if(id != null) {
				//return this.historicoRepository.findByEventoIdTipoBebida(Evento.SAIDA.getEvento(), id);
			}
		}		
		throw new BadRequestException();
	}
}
