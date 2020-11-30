package br.com.coop.coop_api.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.coop.coop_api.entities.PublicacaoOng;
import br.com.coop.coop_api.services.PublicacaoOngService;
import br.com.coop.coop_api.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/publicacao")
@RequiredArgsConstructor
public class PublicacaoOngController {
	
	private final PublicacaoOngService publicacaoOngService;
	
	/* GET em todas as publicações do BD */
	@GetMapping
	public ResponseEntity<Map<String, Object>> getConsultas(
		@RequestParam(defaultValue = "0") int pagina,
		@RequestParam(defaultValue = "10") int quantidade) {
		return publicacaoOngService.getPublicacaoOng(pagina, quantidade);
	}
	
	/* GET publicações referentes à ONG cujo id foi passado na url */
	@GetMapping("/{idOng}")
	public ResponseEntity<Map<String, Object>> getPublicacaoOngEspecifica(
		@PathVariable String idOng,
		@RequestParam(defaultValue = "0") int pagina,
		@RequestParam(defaultValue = "10") int quantidade) {

		return publicacaoOngService.getPublicacaoOngEspecifica(Integer.parseInt(idOng), pagina, quantidade);
	}
	
	/* GET publicação cujo id foi passado na url */
	@GetMapping("/listapublicacoes/{id}")
	public Optional<PublicacaoOng> getById(@PathVariable Integer id){
		
		return publicacaoOngService.getIdPublicacoes(id);
	}
	
	/* GET total de publicações referentes à ONG cujo id foi passado na url */
	@GetMapping("/{idOng}/total")
	public Long getTotalPublicacoes(@PathVariable String idOng) {

		return publicacaoOngService.getTotalPublicacoes(Integer.parseInt(idOng));
	}
	
	/* GET total de visualizações das publicações referentes à ONG cujo id foi passado na url */
	@GetMapping("/{idOng}/total-visualizacoes")
	public Long getTotalVisualizacoes(@PathVariable String idOng) {

		return publicacaoOngService.getTotalVisualizacoes(Integer.parseInt(idOng));
	}
	
	/* GET média de visualizações das publicações referentes à ONG cujo id foi passado na url */
	@GetMapping("/{idOng}/media-visualizacoes")
	public Long getMediaVisualizacoes(@PathVariable String idOng) {

		return publicacaoOngService.getMediaVisualizacoes(Integer.parseInt(idOng));
	}

	/* POST publicacao na tabela de publicações */
	@PostMapping
	public PublicacaoOng Inserir(@RequestBody PublicacaoOng publicacaoOng) {
		// Seta imagem vazia
		publicacaoOng.setImagem_publicacao("");

		// Seta como 0 o númenro de visualizações
		publicacaoOng.setVisualizacoes(0);
		
		publicacaoOngService.Inserir(publicacaoOng);
		
		return publicacaoOng;	
	}
	
	@PutMapping("/foto/{id}")
	public String saveFoto(@PathVariable("id") int id, @RequestParam("foto") MultipartFile multipartFile) throws Exception {
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
		
		PublicacaoOng publicacaoBD = publicacaoOngService.getIdPublicacoes(id).orElseThrow(() -> new IllegalAccessException());

		publicacaoBD.setImagem_publicacao(url);

		publicacaoOngService.Inserir(publicacaoBD);

		return url;
	}
	
	/* PUT alterando a quantidade de visualização da publicação cujo id foi passado na url */
	@PutMapping("/visualizacoes/{id}")
	public PublicacaoOng quantidadeVisualizacoes(@PathVariable("id") int id, @RequestBody PublicacaoOng publicacaoOng) throws Exception {
		PublicacaoOng publicacao = publicacaoOngService.getIdItens(id).orElseThrow(() -> new IllegalAccessException());
		
		publicacao.setVisualizacoes(publicacaoOng.getVisualizacoes());
		
		final PublicacaoOng atualizaVisualizacao = publicacaoOngService.atualizaVisualizacao(publicacao);
		
		return atualizaVisualizacao;
	}
	
	/* DELETE publicação cujo id foi passado na url */
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/delete/{id}")
	public void Delete(@PathVariable("id") int id) {
		
		publicacaoOngService.Delete(id);
	}

}
