package com.coop.coop_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coop.coop_api.entities.Doacao;
import com.coop.coop_api.repositories.DoacaoRepository;

@RestController
@RequestMapping("doacao")
public class DoacaoController {
	
	@Autowired
	private DoacaoRepository doacaoRepository;
	
	@GetMapping()
	public Iterable<Doacao> getConsultas(){
		return doacaoRepository.findAll();
	}
	

}
