package br.com.coop.coop_api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordDTO {
	private String email;
	private String senha;
	private String novaSenha;

}
