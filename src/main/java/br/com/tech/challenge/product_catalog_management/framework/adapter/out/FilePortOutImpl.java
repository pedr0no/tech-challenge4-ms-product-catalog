package br.com.tech.challenge.product_catalog_management.framework.adapter.out;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.tech.challenge.product_catalog_management.application.port.out.FilePortOut;
import br.com.tech.challenge.product_catalog_management.domain.dto.ErrorDTO;
import br.com.tech.challenge.product_catalog_management.domain.dto.FileDTO;
import br.com.tech.challenge.product_catalog_management.framework.adapter.in.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FilePortOutImpl implements FilePortOut {

	@Override
	public void save(FileDTO file) {
		try {
			var pathFile = Paths.get(file.getDirectory(), file.getName());
			Files.createDirectories(pathFile.getParent());
			Files.write(pathFile, file.getBinary());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
					new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
		}
	}

}