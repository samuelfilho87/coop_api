package br.com.coop.coop_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.coop.coop_api.entities.UsuarioOng;
import br.com.coop.coop_api.repositories.UsuarioOngRepository;

@Service
public class OngService {
	@Autowired
	private UsuarioOngRepository repository;
	
	public Iterable<UsuarioOng> getOngs() {
		return repository.findAll();
	}
	
	public Optional<UsuarioOng> getOngsPorUf(String uf) {
		return repository.findByEstado(uf);
	}
	
	public Optional<UsuarioOng[]> getOngsPorCidade(String uf, String cidade) {
		return repository.buscarOngsPorCidade(uf, cidade);
	}
}
