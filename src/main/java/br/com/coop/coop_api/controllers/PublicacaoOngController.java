package br.com.coop.coop_api.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.coop.coop_api.entities.PublicacaoOng;
import br.com.coop.coop_api.services.PublicacaoOngService;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/api/publicacao")
@RequiredArgsConstructor
public class PublicacaoOngController {
	
	private final PublicacaoOngService publicacaoOngService;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> getConsultas(
		@RequestParam(defaultValue = "0") int pagina,
		@RequestParam(defaultValue = "10") int quantidade) {
		return publicacaoOngService.getPublicacaoOng(pagina, quantidade);
	}

	@CrossOrigin
	@PostMapping
	public PublicacaoOng Inserir(@RequestBody PublicacaoOng publicacaoOng) {
		publicacaoOngService.Inserir(publicacaoOng);
		return publicacaoOng;
		
	}
	
	@DeleteMapping("/delete/{id}")
	public void Delete(@PathVariable("id") int id) {
		publicacaoOngService.Delete(id);
	}

}
