package br.com.coop.coop_api.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "doacao")
public class Doacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_doacao")
	private Integer id;
	
	@Column(name = "nome_completo_doador")
	private String nomeCompleto;
	
	@Column(name = "data_entrega_doacao")
	private Date dataEntrega;
	
	@Column(name= "whatsapp_doador")
	private String whatsapp;
	
	@Column(name = "status_doacao")
	private String statusEntrega;
	
	@Column(name = "itens_doacao")
	private String itensDoacao;

	@OneToOne
	@JoinColumn(name = "fk_id_ong")
	private UsuarioOng fkOng;
}