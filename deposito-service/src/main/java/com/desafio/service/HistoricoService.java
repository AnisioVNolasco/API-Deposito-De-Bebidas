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
import com.desafio.model.SecaoModel;
import com.desafio.model.TipoBebidaModel;
import com.desafio.repository.HistoricoRepository;
import com.desafio.util.BadRequestException;
import com.desafio.util.Evento;
import com.desafio.util.NoContentException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/historico")
public class HistoricoService {
	
	@Autowired
	private HistoricoRepository historicoRepository;
	
	@ApiOperation(
			value="Listar todo histórico de entrada", 
			response = HistoricoModel.class,
            responseContainer = "List", 
			notes="Essa operação lista todo o histórico de entrada cadastrada no estoque.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma lista de todo histórico de entrada.",
					response = HistoricoModel.class,
		            responseContainer = "List"
					)
 
	})
	@RequestMapping(value = "/entrada", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<HistoricoModel> consultarEntrada() {

		List<HistoricoModel> list = this.historicoRepository.findByEvento(Evento.ENTRADA.getEvento());
		
		return this.checkList(list);
	}

	@ApiOperation(
			value="Listar todo histórico de saída", 
			response = HistoricoModel.class,
            responseContainer = "List", 
			notes="Essa operação lista todo o histórico de saída cadastrada no estoque.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma lista de todo histórico de saída.",
					response = HistoricoModel.class,
		            responseContainer = "List"
					)
 
	})
	@RequestMapping(value = "/saida", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<HistoricoModel> consultarSaida(@RequestBody EstoqueSaidaDTO saida) {

		List<HistoricoModel> list = this.historicoRepository.findByEvento(Evento.SAIDA.getEvento());
		
		return this.checkList(list);	
	}
	
	@ApiOperation(
			value="Listar todo histórico de entrada ordenada", 
			response = HistoricoModel.class,
            responseContainer = "List", 
			notes="Essa operação lista todo o histórico de entrada cadastrada no estoque de forma ordenada, por data ou pelo id da seção.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma lista de todo histórico de entrada ordenada.",
					response = HistoricoModel.class,
		            responseContainer = "List"
					)
 
	})
	@RequestMapping(value = "/entrada/{parametro}/{ordem}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<HistoricoModel> consultarEntrada(@PathVariable("parametro") String parametro, @PathVariable("ordem") String ordem) {
		if(parametro.equals("data")) {
			if(ordem.equals("desc")) {
				return this.checkList(this.historicoRepository.findByEventoOrderByDataHoraDesc(Evento.ENTRADA.getEvento()));
			} else if(ordem.equals("asc")) {
				return this.checkList(this.historicoRepository.findByEventoOrderByDataHora(Evento.ENTRADA.getEvento()));
			}			
		} else if(parametro.equals("secao")) {
			if(ordem.equals("desc")) {
				return this.checkList(this.historicoRepository.findByEventoOrderByIdSecaoDesc(Evento.ENTRADA.getEvento()));
			} else if(ordem.equals("asc")) {
				return this.checkList(this.historicoRepository.findByEventoOrderByIdSecao(Evento.ENTRADA.getEvento()));
			}
		}
		throw new BadRequestException();
	}
	
	@ApiOperation(
			value="Listar todo histórico de saída ordenada", 
			response = HistoricoModel.class,
            responseContainer = "List", 
			notes="Essa operação lista todo o histórico de saída cadastrada no estoque de forma ordenada, por data ou pelo id da seção.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma lista de todo histórico de saída ordenada.",
					response = HistoricoModel.class,
		            responseContainer = "List"
					)
 
	})
	@RequestMapping(value = "/saida/{parametro}/{ordem}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<HistoricoModel> consultarSaida(@PathVariable("parametro") String parametro, @PathVariable("ordem") String ordem) {
		if(parametro.equals("data")) {
			if(ordem.equals("desc")) {
				return this.checkList(this.historicoRepository.findByEventoOrderByDataHoraDesc(Evento.SAIDA.getEvento()));
			} else if(ordem.equals("asc")) {
				return this.checkList(this.historicoRepository.findByEventoOrderByDataHora(Evento.SAIDA.getEvento()));
			}			
		} else if(parametro.equals("secao")) {
			if(ordem.equals("desc")) {
				return this.checkList(this.historicoRepository.findByEventoOrderByIdSecaoDesc(Evento.SAIDA.getEvento()));
			} else if(ordem.equals("asc")) {
				return this.checkList(this.historicoRepository.findByEventoOrderByIdSecao(Evento.SAIDA.getEvento()));
			}
		}
		throw new BadRequestException();
	}
	
	@ApiOperation(
			value="Listar todo histórico de entrada ou saída de uma seção", 
			response = HistoricoModel.class,
            responseContainer = "List", 
			notes="Essa operação lista todo o histórico de entrada ou saída cadastrada no estoque de determinada seção, pelo id da seção.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma lista de todo histórico de entrada ou saída de uma determinada seção.",
					response = HistoricoModel.class,
		            responseContainer = "List"
					)
 
	})
	@RequestMapping(value = "/{evento}/secao={id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<HistoricoModel> consultarEntradaSaidaSecao(@PathVariable("evento") String evento, @PathVariable("id") String id) {
		if(Evento.ENTRADA.getEvento().equals(evento) || Evento.SAIDA.getEvento().equals(evento)) {
			try {
				Integer idInt = Integer.valueOf(id);
				if(idInt != null) {
					List<HistoricoModel> list = this.historicoRepository.findByEventoAndIdSecao(evento, idInt);
					
					return this.checkList(list); 
				}
			} catch (NumberFormatException e) {
				throw new BadRequestException();
			}
		}
		
		throw new BadRequestException();
	}
	
	@ApiOperation(
			value="Listar todo histórico de entrada ou saída de um tipo de bebida", 
			response = HistoricoModel.class,
            responseContainer = "List", 
			notes="Essa operação lista todo o histórico de entrada ou saída cadastrada no estoque de determinado tipo de bebida, pelo id do tipo de bebida.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma lista de todo histórico de entrada ou saída de uma determinado tipo de bebida.",
					response = HistoricoModel.class,
		            responseContainer = "List"
					)
 
	})
	@RequestMapping(value = "/{evento}/tipobebida={id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<HistoricoModel> consultarEntradaSaidaTipoBebida(@PathVariable("evento") String evento, @PathVariable("id") String id) {
		if(Evento.ENTRADA.getEvento().equals(evento) || Evento.SAIDA.getEvento().equals(evento)) {
			try {
				Integer idInt = Integer.valueOf(id);
				if(idInt != null) {
					List<HistoricoModel> list = this.historicoRepository.findByEventoAndIdTipoBebida(evento, idInt);
					
					return this.checkList(list);
				}
			} catch (NumberFormatException e) {
				throw new BadRequestException();
			}
		}
		
		throw new BadRequestException();
	}
	
	private List<HistoricoModel> checkList(List<HistoricoModel> list){
		if(list != null && !list.isEmpty()) {
			return list;	
		}
		
		throw new NoContentException(); 
	}
}