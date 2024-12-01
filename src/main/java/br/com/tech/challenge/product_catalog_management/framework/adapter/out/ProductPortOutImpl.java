package br.com.tech.challenge.product_catalog_management.framework.adapter.out;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.tech.challenge.product_catalog_management.application.port.out.ProductPortOut;
import br.com.tech.challenge.product_catalog_management.domain.dto.ErrorDTO;
import br.com.tech.challenge.product_catalog_management.domain.entity.ProductEntity;
import br.com.tech.challenge.product_catalog_management.domain.enums.ProductStatus;
import br.com.tech.challenge.product_catalog_management.framework.adapter.in.exception.BusinessException;
import br.com.tech.challenge.product_catalog_management.framework.adapter.out.repository.CatalogRepository;

@Component
public class ProductPortOutImpl implements ProductPortOut {

	@Autowired
	private CatalogRepository repository;

	@Override
	public List<ProductEntity> findAllProducts() {
		return repository.findAll();
	}

	@Override
	public ProductEntity findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new BusinessException(HttpStatus.BAD_REQUEST,
				new ErrorDTO(HttpStatus.BAD_REQUEST, "Não existe produto com o id informado")));
	}

	@Override
	public List<ProductEntity> findProductsByBrandAndGender(String brand, char gender) {
		return repository.findByBrandAndGenderAndStatus(brand, gender, ProductStatus.IN_STOCK.name());
	}

	@Override
	public void updateProduct(ProductEntity entity) {
		repository.save(entity);
	}

}
