package br.com.coop.coop_api.util;

import lombok.Data;

@Data
public class Mail {
	private String fromEmail;
    private String to;
    private String subject;
    private String fromName;
}
