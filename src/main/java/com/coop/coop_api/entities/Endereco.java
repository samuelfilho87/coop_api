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
@Table(name = "endereco")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_endereco;
	
	@Column
	private String logradouro;
	
	@Column
	private String numero;
	
	@Column
	private String complemento;
	
	@Column
	private String cep;

	@Column
	private String estado;
	
	@Column
	private String cidade;
	
	@Column
	private Integer fk_id_ong;
}
