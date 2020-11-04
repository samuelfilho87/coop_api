package com.coop.coop_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coop.coop_api.entities.Endereco;
import com.coop.coop_api.services.EnderecoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/enderecos")
@RequiredArgsConstructor
public class EnderecoController {
	
	private final EnderecoService enderecoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Endereco salvar(@RequestBody Endereco endereco) {		
		return enderecoService.salvar(endereco);
	}

	@GetMapping("/uf")
	public List<Object[]> getEnderecos(){ 
		return enderecoService.getEstados();
	}
	
	@GetMapping("/cidades/{uf}")
	public List<Object[]> getCidadesDaUf(@PathVariable String uf){ 
		return enderecoService.getCidadesDaUf(uf);
	}
}
