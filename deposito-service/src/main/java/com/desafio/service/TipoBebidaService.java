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

import com.desafio.dto.TipoBebidaDTO;
import com.desafio.model.ResponseModel;
import com.desafio.model.TipoBebidaModel;
import com.desafio.repository.TipoBebidaRepository;
import com.desafio.util.NoContentException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/tipobebida")
public class TipoBebidaService {
	@Autowired
	private TipoBebidaRepository tipoBebidaRepository; 
 
	@ApiOperation(
			value="Cadastrar um novo tipo de bebida", 
			response=ResponseModel.class, 
			notes="Essa operação salva um novo registro com as informações do tipo de bebida.")
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
	public @ResponseBody ResponseModel salvar(@RequestBody TipoBebidaDTO tipoBebida){
  
		try {
			if(tipoBebida.getNome() != null && !tipoBebida.getNome().isEmpty() && tipoBebida.getCapacidadeMaxima() != null) {
				this.tipoBebidaRepository.save(new TipoBebidaModel(tipoBebida.getNome(), tipoBebida.getCapacidadeMaxima()));
				return new ResponseModel(1,"Tipo de bebida salvo com sucesso!");
			}
			
			return new ResponseModel(0,"Novo tipo de bebida não cadastrada. Para cadastrar um novo tipo de bebida, deve-se informar o nome e a capacidade máxima de armazenamento");
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());			
		}
	}
 
	@ApiOperation(
			value="Listar todos os tipos de bebidas", 
			response = TipoBebidaModel.class,
            responseContainer = "List", 
			notes="Essa operação lista todos os tipos de bebidas cadastradas.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma lista de tipos de bebidas.",
					response = TipoBebidaModel.class,
		            responseContainer = "List"
					)
 
	})
	@RequestMapping(value="", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<TipoBebidaModel> consultar(){
 		
		List<TipoBebidaModel> list = this.tipoBebidaRepository.findAll();
		
		if(list != null && !list.isEmpty()) {
			return list;	
		}
		
		throw new NoContentException(); 
	}
 
	@ApiOperation(
			value="Obter tipo de bebida pelo id", 
			response = TipoBebidaModel.class,
			notes="Essa operação recupera o tipo de bebida cadastrado com id informado.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um tipo de bebida.",
					response = TipoBebidaModel.class
					)
	})
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody TipoBebidaModel buscar(@PathVariable("id") Integer id){
		
		TipoBebidaModel tipoBedida = this.tipoBebidaRepository.findById(id);
		
		if(tipoBedida != null) {
			return tipoBedida;	
		}
		
		throw new NoContentException(); 
	}
	
	@ApiOperation(
			value="Obter tipo de bebida pelo nome", 
			response = TipoBebidaModel.class,
			notes="Essa operação recupera o tipo de bebida cadastrado com nome informado.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um tipo de bebida.",
					response = TipoBebidaModel.class
					)
	})
	@RequestMapping(value="/{nome}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody TipoBebidaModel buscar(@PathVariable("nome") String nome){
 
		TipoBebidaModel tipoBedida = this.tipoBebidaRepository.findByNome(nome);
		
		if(tipoBedida != null) {
			return tipoBedida;	
		}
		
		throw new NoContentException();
	}
}
