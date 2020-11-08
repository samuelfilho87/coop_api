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
	public UsuarioOng alterar(@PathVariable("id") int id, @RequestBody UsuarioOng ong) throws Exception {
		UsuarioOng ongBD = repository.findById(id)
				.orElseThrow(() -> new IllegalAccessException());
		
		ongBD.setEmail(ong.getEmail());
		ongBD.setSenha(ong.getSenha());
		ongBD.setWhatsapp_ong(ong.getWhatsapp_ong());
		ongBD.setSobre_ong(ong.getSobre_ong());
		ongBD.setArea_atuacao_ong(ong.getArea_atuacao_ong());
		ongBD.setFacebook_ong(ong.getFacebook_ong());
		ongBD.setInstagram_ong(ong.getInstagram_ong());
		ongBD.setLogradouro_local_ong(ong.getLogradouro_local_ong());
		ongBD.setNumero_local_ong(ong.getNumero_local_ong());
		ongBD.setComplemento_local_ong(ong.getComplemento_local_ong());
		ongBD.setCep_local_ong(ong.getCep_local_ong());
		ongBD.setEstado(ong.getEstado());
		ongBD.setCidade_local_ong(ong.getCidade_local_ong());
		
		final UsuarioOng alteraOng = repository.save(ongBD);
		
		return alteraOng;
		
	}


}
