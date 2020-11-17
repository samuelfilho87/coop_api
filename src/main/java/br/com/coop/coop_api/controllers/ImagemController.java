package br.com.coop.coop_api.controllers;

import java.io.IOException;
import java.io.RandomAccessFile;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/api/imagem")
@RequiredArgsConstructor
public class ImagensController {
	
	@GetMapping("/{nomeImagem}")
	public ResponseEntity<byte[]> getImagem(@PathVariable String nomeImagem) throws IOException {
	    RandomAccessFile f = new RandomAccessFile("uploads-ongs/" + nomeImagem, "r");
	    byte[] b = new byte[(int)f.length()];
	    f.readFully(b);
	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_PNG);


	    return new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);
	}
}
