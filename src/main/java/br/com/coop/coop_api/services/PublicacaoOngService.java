package br.com.coop.coop_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.coop.coop_api.entities.PublicacaoOng;
import br.com.coop.coop_api.repositories.PublicacaoOngRepository;

@Service
public class PublicacaoOngService {
	@Autowired
	private PublicacaoOngRepository repository;
	
	@GetMapping
	public Iterable<PublicacaoOng> getPublicacaoOng(){
		return repository.findAll();
	}
	
	/*@PostMapping
	public PublicacaoOng Inserir(PublicacaoOng publicacaoOng) {
		repository.save(publicacaoOng);
		return publicacaoOng;
	}*/
	
}
