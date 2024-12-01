package br.com.tech.challenge.product_catalog_management.application.port.in;

import java.util.List;

import br.com.tech.challenge.product_catalog_management.domain.dto.FileDTO;
import br.com.tech.challenge.product_catalog_management.domain.dto.ProductDTO;

public interface ProductServiceUseCase {
	
	void saveFile(FileDTO file);

	ProductDTO searchProductById(Long id);
	
	List<ProductDTO> searchProductsByBrandAndGender(String brand, char gender);
	
	void updateProduct(Long id, ProductDTO body);
	
}