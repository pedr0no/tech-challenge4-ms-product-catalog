package br.com.tech.challenge.product_catalog_management.application.port.out;

import java.util.List;

import br.com.tech.challenge.product_catalog_management.domain.entity.ProductEntity;

public interface ProductPortOut {

	List<ProductEntity> findAllProducts();

	ProductEntity findById(Long id);
	
	List<ProductEntity> findProductsByBrandAndGender(String brand, char gender);
	
	void updateProduct(ProductEntity entity);

}
