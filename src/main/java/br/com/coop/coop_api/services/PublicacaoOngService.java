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
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.coop.coop_api.entities.PublicacaoOng;
import br.com.coop.coop_api.repositories.PublicacaoOngRepository;

@Service
public class PublicacaoOngService {
	@Autowired
	private PublicacaoOngRepository repository;
	
	public ResponseEntity<Map<String, Object>> getPublicacaoOng(int pagina, int quantidade) {
		try {
			List<PublicacaoOng> publicacoes = new ArrayList<PublicacaoOng>();
			Pageable paginacao = PageRequest.of(pagina, quantidade, Sort.by("id").descending());
			Page<PublicacaoOng> pagePublicacoes = repository.findAll(paginacao);
			Map<String, Object> response = new HashMap<>();

			publicacoes = pagePublicacoes.getContent();

			response.put("publicacoes", publicacoes);
			response.put("paginaAtual", pagePublicacoes.getNumber());
			response.put("totalPublicacoes", pagePublicacoes.getTotalElements());
			response.put("totalPaginas", pagePublicacoes.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Map<String, Object>> getPublicacaoOngEspecifica(int idOng, int pagina, int quantidade) {
		try {
			List<PublicacaoOng> publicacoes = new ArrayList<PublicacaoOng>();
			Pageable paginacao = PageRequest.of(pagina, quantidade);
			Page<PublicacaoOng> pagePublicacoes = repository.findByFkOng(idOng, paginacao);
			Map<String, Object> response = new HashMap<>();

			publicacoes = pagePublicacoes.getContent();

			response.put("publicacacoes", publicacoes);
			response.put("paginaAtual", pagePublicacoes.getNumber());
			response.put("totalDoacoes", pagePublicacoes.getTotalElements());
			response.put("totalPaginas", pagePublicacoes.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public PublicacaoOng Inserir(PublicacaoOng publicacaoOng) {
		repository.save(publicacaoOng);
		return publicacaoOng;
	}
	
	public void Delete(int id) {
		repository.deleteById(id);
	}
	
	public Optional<PublicacaoOng>getIdPublicacoes(int id){
		return repository.findById(id);
	}
	
	public Long getTotalPublicacoes(int idOng) {		
		return repository.countByPublicacoes(idOng);
	}
	
	public PublicacaoOng atualizaVisualizacao(PublicacaoOng publicacaoOng) {
		repository.save(publicacaoOng);
		return publicacaoOng;
	}
	
	public Optional<PublicacaoOng>getIdItens(int id){
		return repository.findById(id);
	}
	
}
