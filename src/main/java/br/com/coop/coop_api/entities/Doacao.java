package br.com.coop.coop_api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private Integer id_doacao;
	
	@Column(name = "nome_completo_doador")
	private String nome_completo;
	
	@Column(name = "data_entrega_doacao")
	private String data_entrega;
	
	@Column(name= "whatsapp_doador")
	private String whatsapp;
	
	@Column(name = "status_doacao")
	private String status_doacao;
	
	@Column(name = "itens_doacao")
	private String itens;

	@ManyToOne
	@JoinColumn(name = "fk_id_ong")
	private UsuarioOng ong;

}