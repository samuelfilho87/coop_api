package com.coop.coop_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coop.coop_api.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
	String queryUf = "select distinct estado FROM endereco order by estado asc";
	String queryCidade = "select distinct cidade FROM endereco where estado like :uf order by cidade asc";
	
	@Query(value = queryUf, nativeQuery = true)
	List<Object[]> buscarEstados();
	
	@Query(value = queryCidade, nativeQuery = true)
	List<Object[]> buscarCidadesPorUf(String uf);
}
