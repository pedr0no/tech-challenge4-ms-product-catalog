package br.com.tech.challenge.product_catalog_management.domain.dto;

import java.math.BigDecimal;

import br.com.tech.challenge.product_catalog_management.domain.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
	private String name;
	private String brand;
	private String color;
	private char gender;
	private Integer quantity;
	private BigDecimal price;
	private ProductStatus status;

}
