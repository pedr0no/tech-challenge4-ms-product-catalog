package br.com.tech.challenge.product_catalog_management.domain.dto;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDTO {
	
	private String name;
	private String directory;
	private byte[] binary;
	private Instant date;

	public FileDTO(String name, String directory, byte[] binary) {
		this.name = name;
		this.directory = directory;
		this.binary = binary;
		this.date = Instant.now();
	}
	
}