package br.com.coop.coop_api.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coop.coop_api.entities.UsuarioOng;

public interface UsuarioOngRepository extends JpaRepository<UsuarioOng, Integer> {
	Optional<UsuarioOng> findByEmail(String email);
	
	Page<UsuarioOng> findByEstado(String uf, Pageable paginacao);
	
	Page<UsuarioOng> findByEstadoAndCidade(String uf, String cidade, Pageable paginacao);	
	

}
