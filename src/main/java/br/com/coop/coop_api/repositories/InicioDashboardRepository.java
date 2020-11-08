package br.com.coop.coop_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.coop.coop_api.entities.Doacao;

public interface InicioDashboardRepository extends CrudRepository<Doacao, Integer> {

	String query = "select id_doacao, nome_completo_doador, itens_doacao from coop_bd.doacao;";
	@Query (value = query, nativeQuery = true)
	List<Object[]>busca();
}
