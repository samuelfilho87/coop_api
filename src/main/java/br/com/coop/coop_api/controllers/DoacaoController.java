package br.com.coop.coop_api.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("/{idOng}")
	public ResponseEntity<Map<String, Object>> getDoacoesOng(
		@PathVariable String idOng,
		@RequestParam(defaultValue = "0") int pagina,
		@RequestParam(defaultValue = "10") int quantidade) {

		return doacaoService.getDoacoes(Integer.parseInt(idOng), pagina, quantidade);
	}
	
	@GetMapping("/{idOng}/total")
	public Long getDoacoesTotalStatus(
		@PathVariable String idOng,
		@RequestParam(defaultValue = "Aguardando") String status) {

		return doacaoService.getDoacoesTotalStatus(Integer.parseInt(idOng), status);
	}

	@CrossOrigin
	@PostMapping
	public Doacao Inserir(@RequestBody Doacao doacao) {
		doacaoService.Inserir(doacao);
		return doacao;

	}
	
	@PutMapping("/edita/{id}")
	public Doacao alterarItensDoacao(@PathVariable("id") int id, @RequestBody Doacao doacao) throws Exception {
		Doacao doacaoOng = doacaoService.getIdItens(id).orElseThrow(() -> new IllegalAccessException());
		
		doacaoOng.setItensDoacao(doacao.getItensDoacao());
		
		final Doacao alteraItens = doacaoService.EditaItensDoacoes(doacaoOng);
		
		return alteraItens;
	}
	
	@GetMapping("/listadoacoes/{id}")
	public Optional<Doacao> getById(@PathVariable Integer id){
		return doacaoService.getIdDoacao(id);
	}
	

}
