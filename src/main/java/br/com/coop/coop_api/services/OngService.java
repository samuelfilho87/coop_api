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

import br.com.coop.coop_api.entities.UsuarioOng;
import br.com.coop.coop_api.repositories.UsuarioOngRepository;

@Service
public class OngService {
	@Autowired
	private UsuarioOngRepository repository;

	public ResponseEntity<Map<String, Object>> getOngs(int pagina, int quantidade) {
		try {
			List<UsuarioOng> ongs = new ArrayList<UsuarioOng>();
			Pageable paginacao = PageRequest.of(pagina, quantidade, Sort.by("id").descending());
			Page<UsuarioOng> pageOngs = repository.findAll(paginacao);
			Map<String, Object> response = new HashMap<>();

			ongs = pageOngs.getContent();

			response.put("ongs", ongs);
			response.put("paginaAtual", pageOngs.getNumber());
			response.put("totalOngs", pageOngs.getTotalElements());
			response.put("totalPaginas", pageOngs.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Map<String, Object>> getOngsPorUf(String uf, int pagina, int quantidade) {
		try {
			List<UsuarioOng> ongs = new ArrayList<UsuarioOng>();
			Pageable paginacao = PageRequest.of(pagina, quantidade, Sort.by("id").descending());
			Page<UsuarioOng> pageOngs = repository.findByEstado(uf, paginacao);
			Map<String, Object> response = new HashMap<>();

			ongs = pageOngs.getContent();

			response.put("ongs", ongs);
			response.put("paginaAtual", pageOngs.getNumber());
			response.put("totalOngs", pageOngs.getTotalElements());
			response.put("totalPaginas", pageOngs.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Map<String, Object>> getOngsPorCidade(String uf, String cidade, int pagina, int quantidade) {
		try {
			List<UsuarioOng> ongs = new ArrayList<UsuarioOng>();
			Pageable paginacao = PageRequest.of(pagina, quantidade, Sort.by("id").descending());
//			Page<UsuarioOng> pageOngs = repository.buscarOngsPorCidade(cidade, paginacao);
			Page<UsuarioOng> pageOngs = repository.findByEstadoAndCidade(uf, cidade, paginacao);
			Map<String, Object> response = new HashMap<>();

			ongs = pageOngs.getContent();

			response.put("ongs", ongs);
			response.put("paginaAtual", pageOngs.getNumber());
			response.put("totalOngs", pageOngs.getTotalElements());
			response.put("totalPaginas", pageOngs.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public UsuarioOng salvaDoacao(UsuarioOng ong) {
		repository.save(ong);
		return ong;
	}

	public Optional<UsuarioOng> getIdDoacao(int id) {
		return repository.findById(id);
	}
	
	
	public UsuarioOng Inserir(UsuarioOng usuarioOng) {
		repository.save(usuarioOng);
		return usuarioOng;
	}
	
	public Optional<UsuarioOng>getIdOng(int id){
		return repository.findById(id);
	}
}
