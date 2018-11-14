package com.desafio;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.desafio.model.SecaoModel;
import com.desafio.model.TipoBebidaModel;
import com.desafio.repository.SecaoRepository;
import com.desafio.repository.TipoBebidaRepository;

@SpringBootApplication
public class DepositoServiceApplication {

	@Autowired
	private TipoBebidaRepository tipoBebidaRepository;
	
	@Autowired
	private SecaoRepository secaoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DepositoServiceApplication.class, args);
		
	}
	
	@Bean
	InitializingBean sendDatabase() {
	    return () -> {
	    	tipoBebidaRepository.save(new TipoBebidaModel("Alcoólica", 500.0));
	    	tipoBebidaRepository.save(new TipoBebidaModel("Não alcoólica", 400.0));
	    	
	    	secaoRepository.save(new SecaoModel("Seção 1", 0.0));
	    	secaoRepository.save(new SecaoModel("Seção 2", 0.0));
	    	secaoRepository.save(new SecaoModel("Seção 3", 0.0));
	    	secaoRepository.save(new SecaoModel("Seção 4", 0.0));
	    	secaoRepository.save(new SecaoModel("Seção 5", 0.0));
	    };
   }

	
	
}
