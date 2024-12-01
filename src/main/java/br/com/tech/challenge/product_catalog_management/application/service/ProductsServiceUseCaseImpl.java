package br.com.tech.challenge.product_catalog_management.application.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.tech.challenge.product_catalog_management.application.port.in.ProductServiceUseCase;
import br.com.tech.challenge.product_catalog_management.application.port.out.FilePortOut;
import br.com.tech.challenge.product_catalog_management.application.port.out.JobPortOut;
import br.com.tech.challenge.product_catalog_management.application.port.out.ProductPortOut;
import br.com.tech.challenge.product_catalog_management.application.service.mapper.Mapper;
import br.com.tech.challenge.product_catalog_management.domain.dto.FileDTO;
import br.com.tech.challenge.product_catalog_management.domain.dto.ProductDTO;

@Service
public class ProductsServiceUseCaseImpl implements ProductServiceUseCase {

	@Autowired
	private FilePortOut filePortOut;

	@Autowired
	private JobPortOut jobPortOut;

	@Autowired
	private ProductPortOut productPortOut;

	@Value("${file.save.location}")
	private String directory;

	@Override
	public void saveFile(FileDTO file) {
		filePortOut.save(file);
		jobPortOut.execute(file);
	}

	@Scheduled(cron = "0 0 12 * * ?")
	public void saveFileScheduled() throws IOException {
		var resource = new ClassPathResource(directory + "/product-data.csv");
		var file = resource.getFile();
		byte[] readFileToByteArray = FileUtils.readFileToByteArray(file);
		var dto = new FileDTO("product-data.csv", directory, readFileToByteArray);
		jobPortOut.execute(dto);
	}

	@Override
	public ProductDTO searchProductById(Long id) {
		return Mapper.mapToProductDTO(productPortOut.findById(id));
	}

	@Override
	public List<ProductDTO> searchProductsByBrandAndGender(String brand, char gender) {
		return Mapper.mapToListProductDTO(productPortOut.findProductsByBrandAndGender(brand, gender));
	}

	@Override
	public void updateProduct(Long id, ProductDTO body) {
		productPortOut.updateProduct(null);
	}

}