package br.com.coop.coop_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.coop.coop_api.dto.CredenciaisDTO;
import br.com.coop.coop_api.dto.ForgottenPasswordDTO;
import br.com.coop.coop_api.dto.TokenDTO;
import br.com.coop.coop_api.entities.UsuarioOng;
import br.com.coop.coop_api.exceptions.SenhaInvalidaException;
import br.com.coop.coop_api.services.JwtService;
import br.com.coop.coop_api.services.UsuarioServiceImpl;
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
			
			System.out.println(usuario);
			
			usuarioService.autenticar(usuario);
			
			String token = jwtService.gerarToken(usuario);
			
			return new TokenDTO(usuarioService.getIdUsuarioLogado(usuario.getEmail()), usuario.getEmail(), token);
		} catch(UsernameNotFoundException | SenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}
	
	@PostMapping("/forgot-password")
    public ResponseEntity<?> generateAuthenticatedLink(@RequestBody ForgottenPasswordDTO data) throws UsernameNotFoundException {
    	usuarioService.recoverPassword(data);
    	return ResponseEntity.noContent().build();
    }
	
}
