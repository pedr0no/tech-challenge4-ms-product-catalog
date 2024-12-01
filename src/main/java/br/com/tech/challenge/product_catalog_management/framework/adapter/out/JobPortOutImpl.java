package br.com.tech.challenge.product_catalog_management.framework.adapter.out;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.tech.challenge.product_catalog_management.application.port.out.JobPortOut;
import br.com.tech.challenge.product_catalog_management.domain.dto.ErrorDTO;
import br.com.tech.challenge.product_catalog_management.domain.dto.FileDTO;
import br.com.tech.challenge.product_catalog_management.framework.adapter.in.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JobPortOutImpl implements JobPortOut {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier("incrementJob")
	private Job job;

	@Override
	public void execute(FileDTO file) {
		try {
			JobParameters jobParameters = new JobParameters();
			jobLauncher.run(job, jobParameters);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
					new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
		}
	}

}