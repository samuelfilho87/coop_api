package br.com.coop.coop_api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "publicacao_ong")
public class PublicacaoOng {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_publicacao;
	
	@Column
	private String titulo_publicacao;
	
	@Column
	private String imagem_publicacao;
	
	@Column
	private String data_publicacao;
	
	@Column
	private String legenda_publicacao;
		
	@OneToOne
	@JoinColumn(name = "fk_id_ong")
	private UsuarioOng ong;
	
}
