package com.desafio.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.dto.DisponibilidadeEstoqueDTO;
import com.desafio.dto.DisponibilidadeSecaoDTO;
import com.desafio.dto.EspacoEstoqueDTO;
import com.desafio.dto.EspacoSecaoDTO;
import com.desafio.dto.EstoqueEntradaDTO;
import com.desafio.dto.TotalBebidaDTO;
import com.desafio.model.ResponseModel;
import com.desafio.model.SecaoModel;
import com.desafio.model.TipoBebidaModel;
import com.desafio.repository.HistoricoRepository;
import com.desafio.repository.SecaoRepository;
import com.desafio.repository.TipoBebidaRepository;

@RestController
@RequestMapping("/service")
public class EstoqueService {

	@Autowired
	private SecaoRepository secaoRepository;
	
	@Autowired
	private TipoBebidaRepository tipoBebidaRepository;
	
	@Autowired
	private HistoricoRepository historicoRepository; 
	
	/**
	 * CONSULTAR VOLUME TOTAL DE BEBIDAS NO ESTOQUE
	 * @return
	 */
	@RequestMapping(value="/estoque", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Object[] consultar(){
		
		List<SecaoModel> listSecao = this.secaoRepository.findAll();
		
		List<TipoBebidaModel> listTipoBebidas = this.tipoBebidaRepository.findAll();
		
		Map<Integer, TotalBebidaDTO> listTotais = new HashMap<>();
		
		for (TipoBebidaModel tipoBebida : listTipoBebidas) {
			listTotais.put(tipoBebida.getId(), new TotalBebidaDTO(tipoBebida.getNome()));
		}
		
		for (SecaoModel secaoModel : listSecao) {
			if(secaoModel.getVolume() != null && secaoModel.getVolume() > 0.0) {
				TotalBebidaDTO totalBebidaDTO = listTotais.get(secaoModel.getIdTipoBebida());
				totalBebidaDTO.addTotal(secaoModel.getVolume());
			}
		}
		
		return listTotais.values().toArray();
	}
	
	/**
	 * CONSULTA DISPONIBILIDADE DE ARMAZENAMENTO (ESPAÇO) PELO TIPO DE BEBIDA E VOLUME
	 * @param idTipoBebida, volume
	 * @return 
	 */
	@RequestMapping(value="/estoque/{idTipoBebida}/{volume}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody EspacoEstoqueDTO consultarEspaco(@PathVariable("idTipoBebida") Integer idTipoBebida, @PathVariable("volume") Double volume){
 
		List<SecaoModel> listSecao = this.secaoRepository.findAll();
		
		TipoBebidaModel tipoBebida= this.tipoBebidaRepository.findById(idTipoBebida);
		
		EspacoEstoqueDTO espacoEstoque = new EspacoEstoqueDTO();
		
		for (SecaoModel secao : listSecao) {
			if(secao.getIdTipoBebida() == null || secao.getIdTipoBebida() == idTipoBebida ) {
				Double espaco = tipoBebida.getCapacidadeMaxima() - secao.getVolume();
				espacoEstoque.addEspacoTotal(espaco);				
				espacoEstoque.addEspaco(new EspacoSecaoDTO(secao.getId(), secao.getNome(), espaco));
			}
		}
		
		if(espacoEstoque.getEspacoTotal() < volume) {
			espacoEstoque.setMensagem("Espaço de armazenamento insuficiente");
		} else {
			espacoEstoque.setMensagem("Espaço de armazenamento disponivel");
		}
		return espacoEstoque;
	}
	
	/**
	 * CONSULTA DISPONIBILIDADE DE VENDA PELO TIPO DE BEBIDA
	 * @param idTipoBebida
	 * @return 
	 */
	@RequestMapping(value="/estoque/{idTipoBebida}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody DisponibilidadeEstoqueDTO consultarDisponibilidade(@PathVariable("idTipoBebida") Integer idTipoBebida){
 
		List<SecaoModel> listSecao = this.secaoRepository.findAll();	
		
		DisponibilidadeEstoqueDTO estoque = new DisponibilidadeEstoqueDTO();
		
		for (SecaoModel secao : listSecao) {
			if(secao.getIdTipoBebida() == idTipoBebida ) {
				estoque.addDisponibilidadeTotal(secao.getVolume());				
				estoque.addSecao(new DisponibilidadeSecaoDTO(secao.getId(), secao.getNome(), secao.getVolume()));
			}
		}
		
		return estoque;
	}
	
	/**
	 * ATUALIZAR A SECAO COM ENTRADA DE BEBIDAS
	 * @param estoqueEntrada
	 * @return
	 */
	@RequestMapping(value="/estoque/entrada", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel entrada(@RequestBody EstoqueEntradaDTO entrada){
 
		try {
 
			this.secaoRepository.save(secao);		
 
			return new ResponseModel(1,"Registro atualizado com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());
		}
	}
	
	
}
