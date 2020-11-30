package br.com.coop.coop_api.controllers;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.coop.coop_api.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/api/imagem")
@RequiredArgsConstructor
public class ImagemController {
	
	@GetMapping("/{nomeImagem}")
	public ResponseEntity<byte[]> getImagem(@PathVariable String nomeImagem) throws IOException {
	    RandomAccessFile f = new RandomAccessFile("files/" + nomeImagem, "r");
	    byte[] b = new byte[(int)f.length()];
	    f.readFully(b);
	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_PNG);
	    f.close();

	    return new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@PostMapping("/upload")
	public String saveImg(@RequestParam("image") MultipartFile multipartFile) {
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
		return "http://localhost:8080/" + uploadDir + "/" + fileName;
	}
}
