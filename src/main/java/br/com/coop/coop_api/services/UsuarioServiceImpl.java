package br.com.coop.coop_api.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.coop.coop_api.dto.ForgottenPasswordDTO;
import br.com.coop.coop_api.entities.UsuarioOng;
import br.com.coop.coop_api.exceptions.SenhaInvalidaException;
import br.com.coop.coop_api.repositories.UsuarioOngRepository;
import br.com.coop.coop_api.util.Mail;

@Service
public class UsuarioServiceImpl implements UserDetailsService {
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioOngRepository repository;
	
	@Autowired
    private EmailService emailService;
	
	@Autowired
    private JwtService jwtService;
	
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
	
	public int getIdUsuarioLogado(String email) {
		UsuarioOng usuario = repository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));
		
		return usuario.getId();
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
	
	public Optional<UsuarioOng> getUser(String email) {
		return repository.findByEmail(email);
	}

	   public void recoverPassword(ForgottenPasswordDTO dto) {
	    	Optional<UsuarioOng> userOpt = repository.findByEmail(dto.getEmail());
	    	if(userOpt.isPresent()) {
	    		UsuarioOng user = userOpt.get();
	    		String token = jwtService.gerarToken(user);
	    		
	    		Mail mail = new Mail();
	    		mail.setTo(dto.getEmail());
	    		mail.setSubject("Recuperação de senha - Coop.com.br");
	    		emailService.sendEmail(mail, token);
	    	}
	    }
	
}
