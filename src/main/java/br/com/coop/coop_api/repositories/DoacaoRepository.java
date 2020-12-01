package br.com.coop.coop_api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.coop.coop_api.entities.Doacao;

public interface DoacaoRepository extends JpaRepository<Doacao, Integer> {

	String query2 = "select * from doacao where fk_id_ong = :idOng order by id_doacao desc";
	@Query (value = query2, nativeQuery = true)
	Page<Doacao> findByFkOng(int idOng, Pageable paginacao);

	String query3 = "select count(*) from doacao where fk_id_ong = :idOng and status_doacao = :status";
	@Query (value = query3, nativeQuery = true)
	Long countByStatusEntrega(int idOng, String status);
}