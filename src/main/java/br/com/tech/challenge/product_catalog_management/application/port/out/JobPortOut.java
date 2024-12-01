package br.com.tech.challenge.product_catalog_management.application.port.out;

import br.com.tech.challenge.product_catalog_management.domain.dto.FileDTO;

public interface JobPortOut {
	
	public void execute(FileDTO file);

}
