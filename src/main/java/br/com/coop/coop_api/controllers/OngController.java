package br.com.coop.coop_api.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<Map<String, Object>> getOngsPorUf(
		@PathVariable String uf,
		@RequestParam(defaultValue = "0") int pagina,
		@RequestParam(defaultValue = "10") int quantidade) {
		
		return ongService.getOngsPorUf(uf, pagina, quantidade);
	}
	
	@GetMapping("/{uf}/{cidade}")
	public ResponseEntity<Map<String, Object>> getOngsPorCidade(
		@PathVariable String uf,
		@PathVariable String cidade,
		@RequestParam(defaultValue = "0") int pagina,
		@RequestParam(defaultValue = "10") int quantidade) {
		
		return ongService.getOngsPorCidade(uf, cidade, pagina, quantidade);
	}

	@PutMapping("/edita/{id}")
	public UsuarioOng alteraDadosOng(@PathVariable("id") int id, @RequestBody UsuarioOng ong) throws Exception {
		UsuarioOng ongBD = ongService.getIdDoacao(id).orElseThrow(() -> new IllegalAccessException());

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
		ongBD.setCidade(ong.getCidade());

		final UsuarioOng alteraOng = ongService.salvaDoacao(ongBD);

		return alteraOng;

	}
	
	@GetMapping("/listaOng/{id}")
	public Optional<UsuarioOng> getById(@PathVariable Integer id){
		return ongService.getIdOng(id);
	}
	
}
