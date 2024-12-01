package br.com.tech.challenge.product_catalog_management.framework.adapter.in.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.tech.challenge.product_catalog_management.domain.dto.ProductDTO;

public interface ProductControllerAPI {

	@PostMapping
	ResponseEntity<Void> uploadCsvFile(@RequestParam("file") MultipartFile file);

	@GetMapping
	ResponseEntity<List<ProductDTO>> searchProductsByBrandAndGender(@RequestParam String brand,
			@RequestParam char gender);

	@GetMapping("/{idProduct}")
	ResponseEntity<ProductDTO> searchProduct(@PathVariable Long id);

	@PutMapping("/{idProduct}")
	ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody ProductDTO body);

}
