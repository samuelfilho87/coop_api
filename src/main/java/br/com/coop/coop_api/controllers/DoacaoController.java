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

	/* GET doações referentes à ONG cujo id foi passado na url */
	@GetMapping("/{idOng}")
	public ResponseEntity<Map<String, Object>> getDoacoesOng(
		@PathVariable String idOng,
		@RequestParam(defaultValue = "0") int pagina,
		@RequestParam(defaultValue = "10") int quantidade) {

		return doacaoService.getDoacoes(Integer.parseInt(idOng), pagina, quantidade);
	}
	
	/* GET total de doações referentes à ONG cujo id foi passado na url */
	@GetMapping("/{idOng}/total")
	public Long getDoacoesTotalStatus(
		@PathVariable String idOng,
		@RequestParam(defaultValue = "Aguardando") String status) {

		return doacaoService.getDoacoesTotalStatus(Integer.parseInt(idOng), status);
	}
	
	/* GET doação cujo id foi passado na url */
	@GetMapping("/listadoacoes/{id}")
	public Optional<Doacao> getById(@PathVariable Integer id){
		return doacaoService.getIdDoacao(id);
	}
	
	/* POST doação na tabela de doações */
	@PostMapping
	public Doacao Inserir(@RequestBody Doacao doacao) {
		doacaoService.Inserir(doacao);
		
		return doacao;
	}
	
	/* PUT alterando o status da entrega da doaçao cujo id foi passado na url */
	@PutMapping("/atualizaStatus/{id}")
	public Doacao atualizaStatusEntrega(@PathVariable("id") int id, @RequestBody Doacao doacao) throws Exception {
		Doacao doacaoOng = doacaoService.getIdItens(id).orElseThrow(() -> new IllegalAccessException());
		
		doacaoOng.setStatusEntrega(doacao.getStatusEntrega());
		
		final Doacao atualizaStatus = doacaoService.atualizaStatusEntrega(doacaoOng);
		
		return atualizaStatus;
	}

}
