package com.desafio.service;

import java.time.LocalDateTime;
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
import com.desafio.dto.EstoqueSaidaDTO;
import com.desafio.dto.TotalBebidaDTO;
import com.desafio.model.HistoricoModel;
import com.desafio.model.ResponseModel;
import com.desafio.model.SecaoModel;
import com.desafio.model.TipoBebidaModel;
import com.desafio.repository.HistoricoRepository;
import com.desafio.repository.SecaoRepository;
import com.desafio.repository.TipoBebidaRepository;
import com.desafio.util.Evento;
import com.desafio.util.NoContentException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/estoque")
public class EstoqueService {

	@Autowired
	private SecaoRepository secaoRepository;

	@Autowired
	private TipoBebidaRepository tipoBebidaRepository;

	@Autowired
	private HistoricoRepository historicoRepository;

	@ApiOperation(
			value="Listar todo estoque", 
			response = TotalBebidaDTO.class,
            responseContainer = "List", 
			notes="Essa operação lista todo o estoque de bebidas disponível para venda.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma lista de todo o estoque de bebidas.",
					response = TotalBebidaDTO.class,
		            responseContainer = "List"
					)
 
	})
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Object[] consultar() {

		List<SecaoModel> listSecao = this.secaoRepository.findAll();

		List<TipoBebidaModel> listTipoBebidas = this.tipoBebidaRepository.findAll();

		Map<Integer, TotalBebidaDTO> listTotais = new HashMap<>();

		for (TipoBebidaModel tipoBebida : listTipoBebidas) {
			listTotais.put(tipoBebida.getId(), new TotalBebidaDTO(tipoBebida.getNome()));
		}

		for (SecaoModel secaoModel : listSecao) {
			if (secaoModel.getVolume() != null && secaoModel.getVolume() > 0.0) {
				TotalBebidaDTO totalBebidaDTO = listTotais.get(secaoModel.getIdTipoBebida());
				totalBebidaDTO.addTotal(secaoModel.getVolume());
			}
		}

		if(!listTotais.isEmpty()) {
			return listTotais.values().toArray();	
		}
		
		throw new NoContentException(); 
	}

	@ApiOperation(
			value="Verifica espaço no estoque", 
			response = EspacoEstoqueDTO.class,
			notes="Essa operação verifica se há espaço para um determinado tipo de bebida no estoque, pelo id do tipo de bebida e volume desejado para armazenar.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma mensagem se há espaço suficiente de armazenamento e lista de seçoes disponiveis para o armazenamento.",
					response = EspacoEstoqueDTO.class
					)
 
	})
	@RequestMapping(value = "/{idTipoBebida}/{volume}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody EspacoEstoqueDTO consultarEspaco(@PathVariable("idTipoBebida") Integer idTipoBebida,
			@PathVariable("volume") Double volume) {

		List<SecaoModel> listSecao = this.secaoRepository.findAll();

		TipoBebidaModel tipoBebida = this.tipoBebidaRepository.findById(idTipoBebida);

		EspacoEstoqueDTO espacoEstoque = new EspacoEstoqueDTO();

		for (SecaoModel secao : listSecao) {
			if (secao.getIdTipoBebida() == null || secao.getIdTipoBebida() == idTipoBebida) {
				Double espaco = tipoBebida.getCapacidadeMaxima() - secao.getVolume();
				espacoEstoque.addEspacoTotal(espaco);
				espacoEstoque.addEspaco(new EspacoSecaoDTO(secao.getId(), secao.getNome(), espaco));
			}
		}

		if (espacoEstoque.getEspacoTotal() < volume) {
			espacoEstoque.setMensagem("Espaço de armazenamento insuficiente");
		} else {
			espacoEstoque.setMensagem("Espaço de armazenamento disponivel");
		}
		return espacoEstoque;
	}

	@ApiOperation(
			value="Verifica a disponibilidade de bebida no estoque", 
			response = DisponibilidadeEstoqueDTO.class,
			notes="Essa operação verifica a disponibilidade de determinado tipo de bebida no estoque, disponível para venda.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna a disponibilidade de determinada bebida no estoque.",
					response = DisponibilidadeEstoqueDTO.class
					)
 
	})
	@RequestMapping(value = "/{idTipoBebida}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody DisponibilidadeEstoqueDTO consultarDisponibilidade(
			@PathVariable("idTipoBebida") Integer idTipoBebida) {

		List<SecaoModel> listSecao = this.secaoRepository.findAll();

		DisponibilidadeEstoqueDTO estoque = new DisponibilidadeEstoqueDTO();

		for (SecaoModel secao : listSecao) {
			if (secao.getIdTipoBebida() == idTipoBebida) {
				estoque.addDisponibilidadeTotal(secao.getVolume());
				estoque.addSecao(new DisponibilidadeSecaoDTO(secao.getId(), secao.getNome(), secao.getVolume()));
			}
		}

		return estoque;
	}

	@ApiOperation(
			value="Atualiza entrada de bebida no estoque", 
			response = ResponseModel.class,
			notes="Essa operação atualiza o estoque com a entrada de determinado tipo de bebida, informando a seção pelo id, o tipo de bebida pelo id, o volume a ser armazenado e o responsável pela entrada.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma mensagem de sucesso para a entrada de bebida.",
					response = ResponseModel.class
					),
			@ApiResponse(
					code=500, 
					message="Caso tenha algum erro retorna um ResponseModel com a Exception ou o motivo da impossibilidade da entrada.",
					response=ResponseModel.class
					)
 
	})
	@RequestMapping(value = "/entrada", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel entrada(@RequestBody EstoqueEntradaDTO entrada) {

		try {
			TipoBebidaModel tipoBebida = this.tipoBebidaRepository.findById(entrada.getIdTipoBebida());
			if (tipoBebida != null) {
				SecaoModel secao = this.secaoRepository.findById(entrada.getIdSecao());
				if (secao != null) {
					if (secao.getIdTipoBebida() == tipoBebida.getId() || secao.getIdTipoBebida() == null) {
						Double novoVolume = secao.getVolume() + entrada.getVolumeDeEntrada();
						if (novoVolume <= tipoBebida.getCapacidadeMaxima()) {
							if (entrada.getResponsavel() != null && !entrada.getResponsavel().isEmpty()) {
								HistoricoModel historico = new HistoricoModel();
								historico.setEvento(Evento.ENTRADA.getEvento());
								historico.setDataHora(LocalDateTime.now());
								historico.setResponsavel(entrada.getResponsavel());
								historico.setIdSecao(secao.getId());
								historico.setIdTipoBebida(tipoBebida.getId());
								historico.setVolume(entrada.getVolumeDeEntrada());

								this.historicoRepository.save(historico);

								secao.setIdTipoBebida(tipoBebida.getId());
								secao.setVolume(novoVolume);

								this.secaoRepository.save(secao);

								return new ResponseModel(1, "Registro de entrada atualizado com sucesso!");
							} else {
								return new ResponseModel(0, "Responsável não foi informado!");
							}
						} else {
							return new ResponseModel(0,
									"Esta seção não pode armazenar este volume, excederá o limite de "
											+ tipoBebida.getCapacidadeMaxima() + " para bebida " + tipoBebida.getNome()
											+ "!");
						}
					} else {
						return new ResponseModel(0, "Esta seção não pode armazenar esse tipo de bebida!");
					}
				} else {
					return new ResponseModel(0, "Seção não encontrado!");
				}
			} else {
				return new ResponseModel(0, "Tipo de bebida não encontrado!");
			}
		} catch (Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}

	@ApiOperation(
			value="Atualiza saida de bebida no estoque", 
			response = ResponseModel.class,
			notes="Essa operação atualiza o estoque com a saida de determinado tipo de bebida, informando a seção pelo id, o volume a ser retirado e o responsável pela saída.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma mensagem de sucesso para a saída de bebida.",
					response = ResponseModel.class
					),
			@ApiResponse(
					code=500, 
					message="Caso tenha algum erro retorna um ResponseModel com a Exception ou o motivo da impossibilidade de saída.",
					response=ResponseModel.class
					)
 
	})
	@RequestMapping(value = "/saida", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel saida(@RequestBody EstoqueSaidaDTO saida) {
		
		try {
			SecaoModel secao = this.secaoRepository.findById(saida.getIdSecao());
			if (secao != null) {
				Double novoVolume = secao.getVolume() - saida.getVolumeDeSaida();
				if (novoVolume >= 0.0) {
					if (saida.getResponsavel() != null && !saida.getResponsavel().isEmpty()) {
						HistoricoModel historico = new HistoricoModel();
						historico.setEvento(Evento.SAIDA.getEvento());
						historico.setDataHora(LocalDateTime.now());
						historico.setResponsavel(saida.getResponsavel());
						historico.setIdSecao(secao.getId());
						historico.setIdTipoBebida(secao.getIdTipoBebida());
						historico.setVolume(saida.getVolumeDeSaida());

						this.historicoRepository.save(historico);

						if (novoVolume == 0.0) {
							secao.setIdTipoBebida(null);
						}
						secao.setVolume(novoVolume);

						this.secaoRepository.save(secao);

						return new ResponseModel(1, "Registro de saída atualizado com sucesso!");
					} else {
						return new ResponseModel(0, "Responsável não foi informado!");
					}
				} else {
					return new ResponseModel(0, "Esta seção não possui volume suficiente para essa retirada!");
				}
			} else {
				return new ResponseModel(0, "Seção não encontrado!");
			}
		} catch (Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}

}
