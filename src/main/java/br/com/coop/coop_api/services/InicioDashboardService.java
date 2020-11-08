package br.com.coop.coop_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.coop.coop_api.entities.Doacao;
import br.com.coop.coop_api.repositories.DoacaoRepository;
import br.com.coop.coop_api.repositories.InicioDashboardRepository;

@Service
public class InicioDashboardService {

	@Autowired
	private InicioDashboardRepository repository;
	
	@GetMapping
	public List<Object[]> getLista() {
	   return repository.busca(); 	
	   
	}
	
}
