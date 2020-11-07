package br.com.coop.coop_api.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.coop.coop_api.entities.UsuarioOng;

public interface DadosOngRepository extends CrudRepository<UsuarioOng, Integer> {

}
