package br.com.coop.coop_api.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.coop.coop_api.services.JwtService;
import br.com.coop.coop_api.services.UsuarioServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private JwtService jwtService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(jwtService, usuarioService);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(usuarioService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    List<String> allowedOriginsUrl = new ArrayList<>();
	    allowedOriginsUrl.add("http://localhost:3000");
	    allowedOriginsUrl.add("http://127.0.0.1:3000");
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);

	    config.setAllowedOrigins(allowedOriginsUrl);
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config);
	    return source;
	}
	
//	@Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.headers()
        	.referrerPolicy(ReferrerPolicy.NO_REFERRER);
		http
			.cors().and()
			.csrf().disable()
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/dadosOng/**", "/api/publicacao/**", "/api/usuarios/**", "/api/enderecos", "/api/doacao", "/api/doacao/alterar-status/**", "/api/ongs/**")
					.permitAll()
				.antMatchers(HttpMethod.GET, "/api/publicacao/**", "/api/enderecos/**", "/api/doacao/**", "/api/ongs/**", "/api/dadosOng/**", "/api/itensOng/**", "/api/imagem/**")
					.permitAll()
				.antMatchers(HttpMethod.PUT, "/api/publicacao/**", "/api/ongs/**", "/api/doacao/**", "/api/usuarios/**")
					.permitAll()
				.antMatchers(HttpMethod.DELETE, "/api/publicacao/**", "/api/doacao/**") 
				    .permitAll()		
				.anyRequest().authenticated()
			.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
