package br.com.tech.challenge.product_catalog_management.domain.dto;

import java.time.Instant;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorDTO {

	private HttpStatus code;
	private String message;
	private Instant timestamp;

	public ErrorDTO(HttpStatus code, String message) {
		this.code = code;
		this.message = message;
		this.timestamp = Instant.now();
	}

}
