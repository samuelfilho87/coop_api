package br.com.coop.coop_api.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.coop.coop_api.entities.UsuarioOng;
import br.com.coop.coop_api.services.OngService;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/api/ongs")
@RequiredArgsConstructor
public class OngController {
	
	private final OngService ongService;

	@GetMapping()
	public ResponseEntity<Map<String, Object>> getOngs(
			@RequestParam(defaultValue = "0") int pagina,
	        @RequestParam(defaultValue = "10") int quantidade) {
		return ongService.getOngs(pagina, quantidade);
	}
	
	@GetMapping("/{uf}")
	public Optional<UsuarioOng> getOngsPorUf(@PathVariable String uf) {
		return ongService.getOngsPorUf(uf);
	}
	
	@GetMapping("/{uf}/{cidade}")
	public Optional<UsuarioOng[]> getOngsPorUf(@PathVariable String uf, @PathVariable String cidade) {
		return ongService.getOngsPorCidade(uf, cidade);
	}
}
