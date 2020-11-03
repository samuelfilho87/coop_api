package com.coop.coop_api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "usuario_ong")
public class UsuarioOng {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_ong;
	
	@Column
	private String email;
	
	@Column
	private String senha;
	
	@Column
	private boolean admin;
	
	@Column
	private String nome_ong;
	
	@Column
	private String nome_completo;
	
	@Column
	private String cnpj;
	
	@Column
	private String data_nascimento;
	
	@Column
	private String whatsapp;
	
	@Column
	private String sobre;
	
	@Column
	private String area_atuacao;
	
	@Column
	private String funcao;
	
	@Column
	private boolean trabalha_ong;
	
	@Column
	private String facebook;
	
	@Column
	private String instagram;
}
