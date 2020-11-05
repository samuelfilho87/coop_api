package br.com.coop.coop_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coop.coop_api.entities.UsuarioOng;

public interface UsuarioOngRepository extends JpaRepository<UsuarioOng, Integer> {
	Optional<UsuarioOng> findByEmail(String email);
}
