package br.com.coop.coop_api.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.coop.coop_api.entities.Doacao;

public interface DoacaoRepository extends JpaRepository<Doacao, Integer> {

	String query = "select id_doacao, nome_completo_doador, itens_doacao from coop_bd.doacao";
	@Query (value = query, nativeQuery = true)
	List<Object[]>busca();
	
	String query2 = "select * from doacao where fk_id_ong = :idOng order by id_doacao desc";
	@Query (value = query2, nativeQuery = true)
	Page<Doacao> findByFkOng(int idOng, Pageable paginacao);

}