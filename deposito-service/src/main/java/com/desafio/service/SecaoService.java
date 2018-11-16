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
import com.desafio.util.NoContentException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/secao")
public class SecaoService {
	@Autowired
	private SecaoRepository secaoRepository;
	
	@ApiOperation(
			value="Cadastrar uma nova seção", 
			response=ResponseModel.class, 
			notes="Essa operação salva um novo registro com as informações da seção.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um ResponseModel com uma mensagem de sucesso",
					response=ResponseModel.class
					),
			@ApiResponse(
					code=500, 
					message="Caso tenhamos algum erro vamos retornar um ResponseModel com a Exception",
					response=ResponseModel.class
					)
 
	})
	@RequestMapping(value="", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody String nomeSecao){
  
		try {
			SecaoModel secao = new SecaoModel(nomeSecao, 0.0);
			
			this.secaoRepository.save(secao);
 
			return new ResponseModel(1,"Seção salva com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());			
		}
	}
 
	@ApiOperation(
			value="Listar todas as seções", 
			response = SecaoModel.class,
            responseContainer = "List", 
			notes="Essa operação lista todas as seções cadastradas.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma lista de seções.",
					response = SecaoModel.class,
		            responseContainer = "List"
					)
 
	})
	@RequestMapping(value="", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<SecaoModel> consultar(){
		List<SecaoModel> list = this.secaoRepository.findAll();
		
		if(list != null && !list.isEmpty()) {
			return list;	
		}
		
		throw new NoContentException(); 
	}
 
	@ApiOperation(
			value="Obter seção pelo id", 
			response = SecaoModel.class,
			notes="Essa operação recupera a seção cadastrada com id informado.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma seção.",
					response = SecaoModel.class
					)
	})
	@RequestMapping(value="/id={id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody SecaoModel buscar(@PathVariable("id") Integer id){
		
		SecaoModel secao = this.secaoRepository.findById(id);
		
		if(secao != null) {
			return secao;	
		}
		
		throw new NoContentException(); 
	}
	
	@ApiOperation(
			value="Obter seção pelo nome", 
			response = SecaoModel.class,
			notes="Essa operação recupera a seção cadastrada com nome informado.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma seção.",
					response = SecaoModel.class
					)
	})
	@RequestMapping(value="/nome={nome}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody SecaoModel buscar(@PathVariable("nome") String nome){
		SecaoModel secao = this.secaoRepository.findByNome(nome);
		
		if(secao != null) {
			return secao;	
		}
		
		throw new NoContentException(); 
	}
 
	@ApiOperation(
			value="Exclui a seção pelo id", 
			response=ResponseModel.class, 
			notes="Essa operação exclui o registro com as informações da seção.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um ResponseModel com uma mensagem de sucesso",
					response=ResponseModel.class
					),
			@ApiResponse(
					code=500, 
					message="Caso tenhamos algum erro vamos retornar um ResponseModel com a mensagem ou Exception",
					response=ResponseModel.class
					)
 
	})
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("id") Integer id){
 
		SecaoModel secao = secaoRepository.findById(id);
 
		try {
			if(secao.getVolume() == 0.0) {
				secaoRepository.delete(secao);
				return new ResponseModel(1, "Seção excluída com sucesso!");
			} else {
				return new ResponseModel(0, "Seção não excluída, seção não está vazia!");	
			}
		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}

}
