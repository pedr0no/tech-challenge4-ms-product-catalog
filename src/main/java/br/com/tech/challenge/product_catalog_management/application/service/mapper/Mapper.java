package br.com.tech.challenge.product_catalog_management.application.service.mapper;

import java.util.List;

import br.com.tech.challenge.product_catalog_management.domain.dto.ProductDTO;
import br.com.tech.challenge.product_catalog_management.domain.entity.ProductEntity;
import br.com.tech.challenge.product_catalog_management.domain.enums.ProductStatus;

public class Mapper {

	private Mapper() {

	}

	public static ProductDTO mapToProductDTO(ProductEntity entity) {
		return new ProductDTO(entity.getName(), entity.getBrand(), entity.getColor(), entity.getGender(),
				entity.getQuantity(), entity.getPrice(), ProductStatus.valueOf(entity.getStatus()));
	}

	public static List<ProductDTO> mapToListProductDTO(List<ProductEntity> entities) {
		return entities.stream().map(entity -> mapToProductDTO(entity)).toList();
	}

	public static ProductEntity mapToProductEntity(Long id, ProductDTO body) {
		return new ProductEntity(id, body.getName(), body.getBrand(), body.getColor(), body.getGender(),
				body.getQuantity(), body.getPrice(), body.getStatus().name());
	}

}
