package br.com.coop.coop_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coop.coop_api.entities.Doacao;
import br.com.coop.coop_api.repositories.DoacaoRepository;

@RestController
@RequestMapping("api/doacao")
public class DoacaoController {
	
	@Autowired
	private DoacaoRepository doacaoRepository;
	
	@GetMapping
	public Iterable<Doacao> getConsultas(){
		return doacaoRepository.findAll();
	}
	
	@PostMapping
	public Doacao Inserir(@RequestBody Doacao doacao) {
		doacaoRepository.save(doacao);
		return doacao;
		
	}

}
