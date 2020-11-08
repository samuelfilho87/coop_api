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
@Table(name = "imagem_ong")
public class ImagemOng {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_imagem_ong;
	
	@Column(name = "imagem_ong_1")
	private String imagem_1;
	
	@Column(name = "imagem_ong_2")
	private String imagem_2;
	
	@Column(name = "imagem_ong_3")
	private String imagem_3;
	
	@Column(name = "imagem_ong_4")
	private String imagem_4;
	
	@Column(name = "imagem_ong_5")
	private String imagem_5;
	
	@OneToOne
	@JoinColumn(name = "fk_id_ong")
	private UsuarioOng ong;
	
}
