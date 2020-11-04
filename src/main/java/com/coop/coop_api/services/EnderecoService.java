package com.coop.coop_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coop.coop_api.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository repository;
	
	public List<Object[]> getEstados() {
		return repository.buscarEstados();
	}
	
	public List<Object[]> getCidadesDaUf(String uf) {
		return repository.buscarCidadesPorUf(uf);
	}
}
