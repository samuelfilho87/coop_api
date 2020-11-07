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
	
	public Optional<UsuarioOng> getOngsPorUf(String uf) {
		return repository.findByEstado(uf);
	}
	
	public Optional<UsuarioOng[]> getOngsPorCidade(String uf, String cidade) {
		return repository.buscarOngsPorCidade(uf, cidade);
	}
}
