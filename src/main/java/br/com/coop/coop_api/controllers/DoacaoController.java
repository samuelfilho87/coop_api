package br.com.coop.coop_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coop.coop_api.entities.Doacao;
import br.com.coop.coop_api.services.DoacaoService;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/api/doacao")
@RequiredArgsConstructor
public class DoacaoController {

	private final DoacaoService doacaoService;

	/* Método de consulta para testar inserção de dados no front end */
	@GetMapping
	public Iterable<Doacao> getConsultas() {
		return doacaoService.getDoacao();
	}

	@CrossOrigin
	@PostMapping
	public Doacao Inserir(@RequestBody Doacao doacao) {
		doacaoService.Inserir(doacao);
		return doacao;

	}

	@GetMapping("/lista")
	public List<Object[]> getLista() {
		return doacaoService.getLista();
	}

	@DeleteMapping("/delete/{id}")
	public void Delete(@PathVariable("id") int id) {
		doacaoService.Delete(id);
	}

}
