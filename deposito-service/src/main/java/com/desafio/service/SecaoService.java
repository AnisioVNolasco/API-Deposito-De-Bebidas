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
import com.desafio.model.SecaoModel;
import com.desafio.repository.SecaoRepository;

@RestController
@RequestMapping("/service")
public class SecaoService {
	@Autowired
	private SecaoRepository secaoRepository;
	
	/**
	 * SALVAR UMA NOVA SECAO
	 * @param secao
	 * @return
	 */
	@RequestMapping(value="/secao", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody SecaoModel secao){
  
		try {
 
			this.secaoRepository.save(secao);
 
			return new ResponseModel(1,"Registro salvo com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());			
		}
	}
 
	/**
	 * ATUALIZAR O REGISTRO DE UMA SECAO
	 * @param secao
	 * @return
	 */
	@RequestMapping(value="/secao", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody SecaoModel secao){
 
		try {
 
			this.secaoRepository.save(secao);		
 
			return new ResponseModel(1,"Registro atualizado com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());
		}
	}
 
	/**
	 * CONSULTAR TODAS AS SECOES
	 * @return
	 */
	@RequestMapping(value="/secao", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<SecaoModel> consultar(){
 
		return this.secaoRepository.findAll();
	}
 
	/**
	 * BUSCAR UM TIPO DE BEBIDA PELO ID
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/secao/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody SecaoModel buscar(@PathVariable("id") Integer id){
 
		return this.secaoRepository.findById(id);
	}
	
	/**
	 * BUSCAR UM TIPO DE BEBIDA PELO NOME
	 * @param nome
	 * @return
	 */
	@RequestMapping(value="/secao/{nome}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody SecaoModel buscar(@PathVariable("nome") String nome){
 
		return this.secaoRepository.findByNome(nome);
	}
 
	/***
	 * EXCLUIR UM TIPO DE BEBIDA PELO ID
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/secao/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("id") Integer id){
 
		SecaoModel secao = secaoRepository.findById(id);
 
		try {
 
			secaoRepository.delete(secao);
 
			return new ResponseModel(1, "Registro excluido com sucesso!");
 
		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}

}
