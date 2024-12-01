package br.com.tech.challenge.product_catalog_management.framework.adapter.in.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.tech.challenge.product_catalog_management.domain.dto.ErrorDTO;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorDTO> handleBusinessException(BusinessException e) {
		return ResponseEntity.status(e.getCode()).body(e.getError());
	}

}
