package com.coop.coop_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coop.coop_api.entities.UsuarioOng;

public interface EnderecoRepository extends JpaRepository<UsuarioOng, Integer> {
	String queryUf = "select distinct estado_local_ong FROM usuario_ong order by estado_local_ong asc";
	String queryCidade = "select distinct cidade_local_ong FROM usuario_ong where estado_local_ong like :uf order by cidade_local_ong asc";
	
	@Query(value = queryUf, nativeQuery = true)
	List<Object[]> buscarEstados();
	
	@Query(value = queryCidade, nativeQuery = true)
	List<Object[]> buscarCidadesPorUf(String uf);
}
