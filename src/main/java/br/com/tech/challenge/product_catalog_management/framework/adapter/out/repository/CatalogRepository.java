package br.com.tech.challenge.product_catalog_management.framework.adapter.out.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tech.challenge.product_catalog_management.domain.entity.ProductEntity;

@Repository
public interface CatalogRepository extends JpaRepository<ProductEntity, Long> {

	List<ProductEntity> findByStatus(String status);
	
	List<ProductEntity> findByBrandAndGenderAndStatus(String brand, char gender, String status);
	
}