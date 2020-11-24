package br.com.coop.coop_api.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.coop.coop_api.entities.Doacao;
import br.com.coop.coop_api.repositories.DoacaoRepository;

@Service
public class DoacaoService {
	@Autowired
	private DoacaoRepository repository;
	
	public ResponseEntity<Map<String, Object>> getDoacoes(int idOng, int pagina, int quantidade) {
		try {
			List<Doacao> doacoes = new ArrayList<Doacao>();
			Pageable paginacao = PageRequest.of(pagina, quantidade);
			Page<Doacao> pageOngs = repository.findByFkOng(idOng, paginacao);
			Map<String, Object> response = new HashMap<>();

			doacoes = pageOngs.getContent();

			response.put("doacoes", doacoes);
			response.put("paginaAtual", pageOngs.getNumber());
			response.put("totalDoacoes", pageOngs.getTotalElements());
			response.put("totalPaginas", pageOngs.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public Long getDoacoesTotalStatus(int idOng, String status) {		
		return repository.countByStatusEntrega(idOng, status);
	}

	public Doacao Inserir(Doacao doacao) {
		repository.save(doacao);
		return doacao;
	}	
	
	public Doacao EditaItensDoacoes(Doacao doacao) {
		repository.save(doacao);
		return doacao;
	}
	
	public Doacao alterarStatusDoacao(Doacao doacao) {
		repository.save(doacao);
		return doacao;
	}
	
	public Optional<Doacao>getIdItens(int id){
		return repository.findById(id);
	}
	
	public Optional<Doacao>getIdDoacao(int id){
		return repository.findById(id);
	}
	
}
