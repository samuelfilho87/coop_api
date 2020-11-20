package br.com.coop.coop_api.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.coop.coop_api.entities.UsuarioOng;
import br.com.coop.coop_api.exceptions.SenhaInvalidaException;
import br.com.coop.coop_api.repositories.UsuarioOngRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService {
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioOngRepository repository;
	
	@Transactional
	public UsuarioOng salvar(UsuarioOng usuario) {
		return repository.save(usuario);
	}
	
	public UserDetails autenticar(UsuarioOng usuario) {
		UserDetails user = loadUserByUsername(usuario.getEmail());
		boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword());
		
		if(senhasBatem) {
			return user;
		}
		
		throw new SenhaInvalidaException();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioOng usuario = repository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));
		
		String[] roles = usuario.isTrabalha_ong() ?
				new String[] {"ADMIN", "USER"} : new String[] {"USER"};
				
		return User
				.builder()
				.username(usuario.getEmail())
				.password(usuario.getSenha())
				.roles(roles)
				.build();
	}
	
	public UsuarioOng Inserir(UsuarioOng usuarioOng) {
		 repository.save(usuarioOng);
		 return usuarioOng;
	}
	
}
