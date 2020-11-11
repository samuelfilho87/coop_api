package br.com.coop.coop_api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "usuario_ong")
public class UsuarioOng {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_ong")
	private Integer id;
	
	@Column(name = "email_ong")
	private String email;
	
//	@JsonIgnore
	@Column(name = "senha_ong")
	private String senha;
	
	@Column
	private String nome_ong;
	
	@Column
	private String nome_completo_responsavel;
	
	@Column
	private String email_responsavel;
	
	@Column
	private String whatsapp_responsavel;
	
	@Column
	private String cnpj_ong;
	
	@Column
	private String data_nascimento_responsavel;
	
	@Column
	private String whatsapp_ong;
	
	@Column
	private String sobre_ong;
	
	@Column
	private String area_atuacao_ong;
	
	@Column
	private String funcao_responsavel;
	
	@Column
	private boolean trabalha_ong;
	
	@Column
	private String facebook_ong;
	
	@Column
	private String instagram_ong;
	
	@Column
	private String logradouro_local_ong;
	
	@Column
	private String numero_local_ong;
	
	@Column
	private String complemento_local_ong;
	
	@Column
	private String cep_local_ong;

	@Column(name = "estado_local_ong")
	private String estado;
	
	@Column
	private String cidade_local_ong;
	
	@Column
	private String latitude;
	
	@Column
	private String longitude;
	
	@Column
	private String itens_doacao_requeridos;
}
