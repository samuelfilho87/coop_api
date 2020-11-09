package br.com.coop.coop_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.coop.coop_api.entities.Doacao;
import br.com.coop.coop_api.repositories.DoacaoRepository;

@Service
public class DoacaoService {
	@Autowired
	private DoacaoRepository repository;

	public Iterable<Doacao> getDoacao() {
		return repository.findAll();
	}
	
	public Doacao Inserir(Doacao doacao) {
		repository.save(doacao);
		return doacao;
	}

}
