package com.coop.coop_api.doacao;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.coop.coop_api.entities.Doacao;
import com.coop.coop_api.repositories.DoacaoRepository;

/*
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DoacaoRepositoryTest {
	
	@Autowired
	
	DoacaoRepository doacaoRepository;
	
	@Test
	public void verificaIdNull() {
		Doacao doacao = new Doacao();
		
		this.doacaoRepository.save(doacao);
		
		Doacao doacaoDb = this.doacaoRepository.findyOneByNomeCompleto(doacao.getNome_completo_doador());
		
		Assertions.assertThat(doacaoDb.getId_doacao()).isNotNull();
		
		
	}
	
	
	@Test
	public void doacaoAtualizado() {
		Doacao doacao = new Doacao();
		
		this.doacaoRepository.save(doacao);
		
		doacao.setNome_completo_doador("Jessica Almeida");
		doacao.setEmail_doador("jessica@gmail.com");
		doacao.setWhatsapp_doador("(11) 94422-5454");
		doacao.setStatus_doacao(1);
		doacao.setItens_doacao("Material higiene, Outros");
		
		Doacao doacaoAtualizado = this.doacaoRepository.findyOneByNomeCompleto(doacao.getNome_completo_doador());
		
		Assertions.assertThat(doacaoAtualizado.getId_doacao()).isEqualTo(doacao.getNome_completo_doador());
		
		
	}
	
	@Test
	public void doacaoDeletado() {
		Doacao doacao = new Doacao();
		
		this.doacaoRepository.save(doacao);
		
		this.doacaoRepository.delete(doacao);
		
		Assertions.assertThat(this.doacaoRepository.findyOneByNomeCompleto(doacao.getNome_completo_doador())).isNull();
		
		
	}

}
*/
