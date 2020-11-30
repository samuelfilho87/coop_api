package br.com.coop.coop_api.exceptions;

public class SenhaInvalidaException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SenhaInvalidaException() {
		super("Senha inválida");
	}
}
