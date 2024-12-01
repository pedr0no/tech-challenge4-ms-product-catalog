package br.com.tech.challenge.product_catalog_management.framework.adapter.out.config.increment;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import br.com.tech.challenge.product_catalog_management.domain.entity.ProductEntity;

public class ProductMapper implements FieldSetMapper<ProductEntity> {

	@Override
	public ProductEntity mapFieldSet(FieldSet fieldSet) throws BindException {
		var entity = new ProductEntity();
		entity.setName(fieldSet.readString("name"));
		entity.setBrand(fieldSet.readString("brand"));
		entity.setColor(fieldSet.readString("color"));
		entity.setGender(fieldSet.readChar("gender"));
		entity.setQuantity(fieldSet.readInt("quantity"));
		entity.setPrice(fieldSet.readBigDecimal("price"));
		return entity;
	}

}
