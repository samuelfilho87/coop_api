package br.com.coop.coop_api.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.coop.coop_api.entities.Doacao;

public interface DoacaoRepository extends CrudRepository<Doacao, Integer> {

	/*
	Doacao findyOneByNomeCompleto(String nome_completo_doador);
  */

}
