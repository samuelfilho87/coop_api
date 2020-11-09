package br.com.coop.coop_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.coop.coop_api.entities.UsuarioOng;

public interface UsuarioOngRepository extends JpaRepository<UsuarioOng, Integer> {
	Optional<UsuarioOng> findByEmail(String email);
	Optional<UsuarioOng> findByEstado(String email);
	
	String query = "SELECT * FROM usuario_ong WHERE estado_local_ong LIKE :uf AND cidade_local_ong LIKE :cidade ORDER BY nome_ong";

	@Query(value = query, nativeQuery = true)
	Optional<UsuarioOng[]> buscarOngsPorCidade(String uf, String cidade);
	
}
