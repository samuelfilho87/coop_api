package com.coop.coop_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coop.coop_api.services.EnderecoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/enderecos")
@RequiredArgsConstructor
public class EnderecoController {
	
	private final EnderecoService enderecoService;

	@GetMapping("/uf")
	public List<Object[]> getEnderecos(){ 
		return enderecoService.getEstados();
	}
	
	@GetMapping("/cidades/{uf}")
	public List<Object[]> getCidadesDaUf(@PathVariable String uf){ 
		return enderecoService.getCidadesDaUf(uf);
	}
}
