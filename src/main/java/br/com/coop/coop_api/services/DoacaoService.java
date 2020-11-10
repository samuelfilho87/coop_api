package br.com.coop.coop_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.coop.coop_api.entities.Doacao;
import br.com.coop.coop_api.repositories.DoacaoRepository;

@Service
public class DoacaoService {
	@Autowired
	private DoacaoRepository repository;

	/* Método de consulta para testar inserção de dados no front end */
	public Iterable<Doacao> getDoacao() {
		return repository.findAll();
	}

	public Doacao Inserir(Doacao doacao) {
		repository.save(doacao);
		return doacao;
	}

	public List<Object[]> getLista() {
		return repository.busca();

	}

	public void Delete(int id) {
		repository.deleteById(id);
	}
	
	public Doacao EditaItensDoacoes(Doacao doacao) {
		repository.save(doacao);
		return doacao;
	}
	
	public Optional<Doacao>getIdItens(int id){
		return repository.findById(id);
	}

}
