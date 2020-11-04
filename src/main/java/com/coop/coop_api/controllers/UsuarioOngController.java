package com.coop.coop_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.coop.coop_api.dto.CredenciaisDTO;
import com.coop.coop_api.dto.TokenDTO;
import com.coop.coop_api.entities.UsuarioOng;
import com.coop.coop_api.exceptions.SenhaInvalidaException;
import com.coop.coop_api.services.JwtService;
import com.coop.coop_api.services.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioOngController {
	private final UsuarioServiceImpl usuarioService;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioOng salvar(@RequestBody UsuarioOng usuario) {
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
		
		usuario.setSenha(senhaCriptografada);
		
		return usuarioService.salvar(usuario);
	}
	
	@PostMapping("/auth")
	public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
		try {
			UsuarioOng usuario = UsuarioOng.builder()
					.email(credenciais.getEmail())
					.senha(credenciais.getSenha()).build();
			
			usuarioService.autenticar(usuario);
			
			String token = jwtService.gerarToken(usuario);
			
			return new TokenDTO(usuario.getEmail(), token);
		} catch(UsernameNotFoundException | SenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}
}