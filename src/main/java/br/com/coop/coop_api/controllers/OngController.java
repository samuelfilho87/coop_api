package br.com.coop.coop_api.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.coop.coop_api.entities.UsuarioOng;
import br.com.coop.coop_api.services.OngService;
import br.com.coop.coop_api.util.FileUploadUtil;
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
	
	@GetMapping("/ong/{id}")
	public Optional<UsuarioOng> getOng(@PathVariable Integer id){
		return ongService.getIdOng(id);
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

	@PostMapping("/altera-dados-ong/{id}")
	public UsuarioOng alteraDadosOng(@PathVariable("id") int id, @RequestBody UsuarioOng ong) throws Exception {
		UsuarioOng ongBD = ongService.getIdDadosOng(id).orElseThrow(() -> new IllegalAccessException());

		ongBD.setNome_ong(ong.getNome_ong());
		ongBD.setCnpj_ong(ong.getCnpj_ong());
		ongBD.setEmail(ong.getEmail());
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
	
	@PostMapping("/alterar-itens/{id}")
	public UsuarioOng alteraItensOng(@PathVariable("id") int id, @RequestBody UsuarioOng ong) throws Exception {
		UsuarioOng ongBD = ongService.getIdOng(id).orElseThrow(() -> new IllegalAccessException());

		ongBD.setItens_doacao_requeridos(ong.getItens_doacao_requeridos());

		final UsuarioOng alteraOng = ongService.salvaDoacao(ongBD);

		return alteraOng;
	}
	
	@PutMapping("/logo/{id}")
	public String saveLogo(@PathVariable("id") int id, @RequestParam("logo") MultipartFile multipartFile) throws Exception {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		Date date = new Date();
		String filePrefix = date.getTime() + "-";
		String uploadDir = "files";
		fileName = filePrefix + fileName;
		try {
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("O arquivo não foi salvo");
			return "Error";
		}
		System.out.println("O arquivo foi salvo!");
		
		// Salva URL no BD
		String url = "http://localhost:8080/api/imagem/" + fileName;
		
		UsuarioOng ongBD = ongService.getIdOng(id).orElseThrow(() -> new IllegalAccessException());

		ongBD.setLogo(url);

		ongService.salvaDoacao(ongBD);

		return url;
	}
}
