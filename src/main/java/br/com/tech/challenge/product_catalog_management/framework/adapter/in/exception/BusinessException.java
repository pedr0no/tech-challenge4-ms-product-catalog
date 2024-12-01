package br.com.tech.challenge.product_catalog_management.framework.adapter.in.exception;

import org.springframework.http.HttpStatus;

import br.com.tech.challenge.product_catalog_management.domain.dto.ErrorDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3355813192655802724L;

	private HttpStatus code;
	private ErrorDTO error;

	public BusinessException(HttpStatus code, ErrorDTO error) {
		this.code = code;
		this.error = error;
	}

}