package br.com.coop.coop_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.coop.coop_api.entities.Doacao;
import br.com.coop.coop_api.repositories.DoacaoRepository;

@Service
public class DoacaoService {
	@Autowired
	private DoacaoRepository repository;
	
	@GetMapping
	public Iterable<Doacao> getDoacao(){
		return repository.findAll();
	}
	
	@PostMapping
	public Doacao Inserir(Doacao doacao) {
		repository.save(doacao);
		return doacao;
	}
	
}
