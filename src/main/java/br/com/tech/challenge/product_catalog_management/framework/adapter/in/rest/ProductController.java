package br.com.tech.challenge.product_catalog_management.framework.adapter.in.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.tech.challenge.product_catalog_management.application.port.in.ProductServiceUseCase;
import br.com.tech.challenge.product_catalog_management.domain.dto.ErrorDTO;
import br.com.tech.challenge.product_catalog_management.domain.dto.FileDTO;
import br.com.tech.challenge.product_catalog_management.domain.dto.ProductDTO;
import br.com.tech.challenge.product_catalog_management.framework.adapter.in.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController implements ProductControllerAPI {

	@Autowired
	ProductServiceUseCase useCase;

	@Value("${file.save.location}")
	private String directory;

	@Override
	public ResponseEntity<Void> uploadCsvFile(@RequestParam("file") MultipartFile file) {
		try {
			useCase.saveFile(new FileDTO("product-data.csv", directory, file.getBytes()));
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
					new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
		}
	}

	@Override
	public ResponseEntity<List<ProductDTO>> searchProductsByBrandAndGender(@RequestParam String brand,
			@RequestParam char gender) {
		return ResponseEntity.ok(useCase.searchProductsByBrandAndGender(brand, gender));
	}

	@Override
	public ResponseEntity<ProductDTO> searchProduct(@PathVariable Long id) {
		return ResponseEntity.ok(useCase.searchProductById(id));
	}

	@Override
	public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody ProductDTO body) {
		useCase.updateProduct(id, body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}