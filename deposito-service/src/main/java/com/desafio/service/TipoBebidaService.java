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

import com.desafio.model.ResponseModel;
import com.desafio.model.TipoBebidaModel;
import com.desafio.repository.TipoBebidaRepository;

@RestController
@RequestMapping("/service")
public class TipoBebidaService {
	@Autowired
	private TipoBebidaRepository tipoBebidaRepository; 
 
	/**
	 * SALVAR UM NOVO TIPO DE BEBIDA
	 * @param TipoBebida
	 * @return
	 */
	@RequestMapping(value="/tipobebida", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody TipoBebidaModel tipoBebida){
  
		try {
 
			this.tipoBebidaRepository.save(tipoBebida);
 
			return new ResponseModel(1,"Registro salvo com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());			
		}
	}
 
	/**
	 * ATUALIZAR O REGISTRO DE UM TIPO DE BEBIDA
	 * @param secao
	 * @return
	 */
	@RequestMapping(value="/tipobebida", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody TipoBebidaModel tipoBebida){
 
		try {
 
			this.tipoBebidaRepository.save(tipoBebida);		
 
			return new ResponseModel(1,"Registro atualizado com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());
		}
	}
 
	/**
	 * CONSULTAR TODAS OS TIPOS DE BEBIDAS
	 * @return
	 */
	@RequestMapping(value="/tipobebida", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<TipoBebidaModel> consultar(){
 
		return this.tipoBebidaRepository.findAll();
	}
 
	/**
	 * BUSCAR UM TIPO DE BEBIDA PELO ID
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/tipobebida/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody TipoBebidaModel buscar(@PathVariable("id") Integer id){
 
		return this.tipoBebidaRepository.findById(id);
	}
	
	/**
	 * BUSCAR UM TIPO DE BEBIDA PELO NOME
	 * @param nome
	 * @return
	 */
	@RequestMapping(value="/tipobebida/{nome}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody TipoBebidaModel buscar(@PathVariable("nome") String nome){
 
		return this.tipoBebidaRepository.findByNome(nome);
	}
 
	/***
	 * EXCLUIR UM TIPO DE BEBIDA PELO ID
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/tipobebida/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("id") Integer id){
 
		TipoBebidaModel tipoBebidaModel = tipoBebidaRepository.findById(id);
 
		try {
 
			tipoBebidaRepository.delete(tipoBebidaModel);
 
			return new ResponseModel(1, "Registro excluido com sucesso!");
 
		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}
}
