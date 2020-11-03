package com.coop.coop_api.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "doacao")
public class Doacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_doacao;
	private String nome_completo_doador;
	private Date data_entrega_doacao;
	private String email_doador;
	private String whatsapp_doador;
	private int status_doacao;
	private String itens_doacao;
	
	
	@ManyToOne
	@JoinColumn(name = "fk_id_ong")
	private UsuarioOng ong;

	
	public Doacao() {}
	
	public Doacao(String nome_completo_doador, Date data_entrega_doador, String email_doador, String whatsapp_doador,
			int status_doacao, String itens_doacao) {
		this.nome_completo_doador = nome_completo_doador;
		this.data_entrega_doacao = data_entrega_doador;
		this.email_doador = email_doador;
		this.whatsapp_doador = whatsapp_doador;
		this.status_doacao = status_doacao;
		this.itens_doacao = itens_doacao;
		
	}
	

	public Integer getId_doacao() {
		return id_doacao;
	}
	
	public String getNome_completo_doador() {
		return nome_completo_doador;
	}

	public void setNome_completo_doador(String nome_completo_doador) {
		this.nome_completo_doador = nome_completo_doador;
	}

	public Date getData_entrega_doacao() {
		return data_entrega_doacao;
	}

	public void setData_entrega_doacao(Date data_entrega_doacao) {
		this.data_entrega_doacao = data_entrega_doacao;
	}

	public String getEmail_doador() {
		return email_doador;
	}

	public void setEmail_doador(String email_doador) {
		this.email_doador = email_doador;
	}

	public String getWhatsapp_doador() {
		return whatsapp_doador;
	}

	public void setWhatsapp_doador(String whatsapp_doador) {
		this.whatsapp_doador = whatsapp_doador;
	}

	public int getStatus_doacao() {
		return status_doacao;
	}

	public void setStatus_doacao(int status_doacao) {
		this.status_doacao = status_doacao;
	}

	public String getItens_doacao() {
		return itens_doacao;
	}

	public void setItens_doacao(String itens_doacao) {
		this.itens_doacao = itens_doacao;
	}
	
	public UsuarioOng getOng() {
		return ong;
	}
	
	public void setUsuarioOng(UsuarioOng ong) {
		this.ong = ong;
	}

}
