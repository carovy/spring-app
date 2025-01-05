package org.example.maintenancemicroservice;

import jakarta.annotation.PostConstruct;
import org.example.maintenancemicroservice.utils.DataLoaderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MaintenanceMicroserviceApplication {
	@Autowired
	private DataLoaderHelper dataLoaderHelper;
	public static void main(String[] args) {
		SpringApplication.run(MaintenanceMicroserviceApplication.class, args);
	}
	@PostConstruct
	public void init() {
		dataLoaderHelper.loadMaintenances();
	}
}