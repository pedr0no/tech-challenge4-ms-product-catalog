package br.com.tech.challenge.product_catalog_management.framework.adapter.out.config.increment;

import org.springframework.batch.item.ItemProcessor;

import br.com.tech.challenge.product_catalog_management.domain.entity.ProductEntity;
import br.com.tech.challenge.product_catalog_management.domain.enums.ProductStatus;

public class ProductProcessor implements ItemProcessor<ProductEntity, ProductEntity> {

	@Override
	public ProductEntity process(ProductEntity item) throws Exception {
		item.setStatus(ProductStatus.IN_STOCK.name());
		return item;
	}

}