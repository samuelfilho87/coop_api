package br.com.coop.coop_api.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coop.coop_api.entities.UsuarioOng;
import br.com.coop.coop_api.repositories.DadosOngRepository;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/api/dadosOng")
@RequiredArgsConstructor
public class DadosOngController {
	
	@Autowired
	private DadosOngRepository repository;
	
	@GetMapping
	public Iterable<UsuarioOng> getConsulta(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional <UsuarioOng> ConsultaId(@PathVariable Integer id){
		return repository.findById(id);
	}
	
	@PutMapping("/editar/{id}")
	public UsuarioOng alterar(@PathVariable int id, @RequestBody UsuarioOng ong) throws Exception {
		UsuarioOng ongDB = repository.findById(id).orElseThrow(() -> new IllegalAccessException());
		
		if(ong.getEmail() != ong.getEmail()) {
			ongDB.setEmail(ong.getEmail());
		}
		if(ong.getWhatsapp_ong() != ong.getWhatsapp_ong()) {
			ongDB.setWhatsapp_ong(ong.getWhatsapp_ong());
		}
		if(ong.getSobre_ong() != ong.getSobre_ong()) {
			ongDB.setSobre_ong(ong.getSobre_ong());
		}
		if(ong.getArea_atuacao_ong() != ong.getArea_atuacao_ong()) {
			ongDB.setArea_atuacao_ong(ong.getArea_atuacao_ong());
		}
		if(ong.getFacebook_ong() != ong.getFacebook_ong()) {
			ongDB.setFacebook_ong(ong.getFacebook_ong());
		}
		if(ong.getLogradouro_local_ong() != ong.getLogradouro_local_ong()) {
			ongDB.setLogradouro_local_ong(ong.getLogradouro_local_ong());
		}
		if(ong.getNumero_local_ong() != ong.getNumero_local_ong()) {
			ongDB.setNumero_local_ong(ong.getNumero_local_ong());
		}
		if(ong.getComplemento_local_ong() != ong.getComplemento_local_ong()) {
			ongDB.setComplemento_local_ong(ong.getComplemento_local_ong());
		}
		if(ong.getCep_local_ong() != ong.getCep_local_ong()) {
			ongDB.setCep_local_ong(ong.getCep_local_ong());
		}
		if(ong.getEstado() != ong.getEstado()) {
			ongDB.setEstado(ong.getEstado());
		}
		if(ong.getCidade_local_ong() != ong.getCidade_local_ong()) {
			ongDB.setCidade_local_ong(ong.getCidade_local_ong());
		}
		
		repository.save(ongDB);
		return ongDB;
		
	}
	

}
