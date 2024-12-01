package br.com.tech.challenge.product_catalog_management.framework.adapter.out.config.increment;

import java.io.File;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.PlatformTransactionManager;

import br.com.tech.challenge.product_catalog_management.domain.dto.ErrorDTO;
import br.com.tech.challenge.product_catalog_management.domain.entity.ProductEntity;
import br.com.tech.challenge.product_catalog_management.framework.adapter.in.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class IncrementBatchConfiguration {

	@Autowired
	private PlatformTransactionManager manager;

	@Value("${file.save.location}")
	private String directory;

	@Value("${file.move.location}")
	private String outdatedDirectory;

	@Bean
	public Job incrementJob(@Qualifier("firstStep") Step firstStep, @Qualifier("moveFilesStep") Step moveFilesStep,
			JobRepository jobRepository) {
		return new JobBuilder("import-files", jobRepository).start(firstStep).next(moveFilesStep)
				.incrementer(new RunIdIncrementer()).build();
	}

	@Bean
	public Step firstStep(@Qualifier("reader") ItemReader<ProductEntity> reader,
			@Qualifier("writer") ItemWriter<ProductEntity> writer, @Qualifier("processor") ProductProcessor processor,
			JobRepository jobRepository) {
		return new StepBuilder("firstStep", jobRepository).<ProductEntity, ProductEntity>chunk(1000, manager)
				.reader(reader).processor(processor).writer(writer).allowStartIfComplete(true).build();
	}

	@Bean
	public ItemReader<ProductEntity> reader() {
		return new FlatFileItemReaderBuilder<ProductEntity>().name("read-csv")
				.resource(new FileSystemResource(directory + "/product-data.csv")).comments("--").delimited()
				.delimiter(",").names("name", "brand", "gender", "color", "quantity", "price")
				.fieldSetMapper(new ProductMapper()).build();
	}

	@Bean
	public ItemWriter<ProductEntity> writer(DataSource datasource) {
		return new JdbcBatchItemWriterBuilder<ProductEntity>().dataSource(datasource)
				.sql("INSERT INTO \"products\"(name, brand, color, gender, quantity, price, status)"
						+ "	VALUES (:name, :brand, :color, :gender, :quantity, :price, :status)")
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>()).build();
	}

	@Bean
	public ProductProcessor processor() {
		return new ProductProcessor();
	}

	@Bean
	public Step moveFilesStep(JobRepository jobRepository) {
		return new StepBuilder("moveFilesStep", jobRepository).tasklet(moveFilesTasklet(), manager)
				.allowStartIfComplete(true).build();
	}

	@Bean
	public Tasklet moveFilesTasklet() {
		return (contribution, chunkContext) -> {
			File originFolder = new File(directory);
			File destinationFolder = new File(outdatedDirectory);

			if (!destinationFolder.exists()) {
				destinationFolder.mkdirs();
			}

			File[] files = originFolder.listFiles((dir, name) -> name.endsWith(".csv"));

			if (files != null) {
				for (File file : files) {
					File arquivoDestino = new File(destinationFolder, file.getName());
					if (file.renameTo(arquivoDestino)) {
						log.info("File moved: {}", file.getName());
					} else {
						log.warn("Unable to move file: {}", file.getName());
						throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
								new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to move file"));
					}
				}
			}
			return RepeatStatus.FINISHED;
		};
	}

}
