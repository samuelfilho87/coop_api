package br.com.coop.coop_api.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.coop.coop_api.entities.PublicacaoOng;

public interface PublicacaoOngRepository extends JpaRepository<PublicacaoOng, Integer> {

	String query = "select id_publicacao, titulo_publicacao, visualizacoes, imagem_publicacao, data_publicacao, legenda_publicacao from coop_bd.doacao";
	@Query (value = query, nativeQuery = true)
	List<Object[]> busca();
	
	String query2 = "select * from publicacao_ong where fk_id_ong = :idOng order by id_publicacao desc";
	@Query (value = query2, nativeQuery = true)
	Page<PublicacaoOng> findByFkOng(int idOng, Pageable paginacao);
	
	String query3 = "select count(*) from publicacao_ong where fk_id_ong = :idOng";
	@Query (value = query3, nativeQuery = true)
	Long countByPublicacoes(int idOng);

	String query4 = "select sum(visualizacoes) from publicacao_ong where fk_id_ong = :idOng";
	@Query (value = query4, nativeQuery = true)
	Long countByVisualizacoes(int idOng);
	
	String query5 = "select avg(visualizacoes) from publicacao_ong where fk_id_ong = :idOng";
	@Query (value = query5, nativeQuery = true)
	Long avgByVisualizacoes(int idOng);
}