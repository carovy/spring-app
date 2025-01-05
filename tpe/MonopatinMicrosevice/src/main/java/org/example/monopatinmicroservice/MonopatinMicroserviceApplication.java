package org.example.monopatinmicroservice;

import jakarta.annotation.PostConstruct;
import org.example.monopatinmicroservice.utils.DataLoaderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.text.ParseException;

@SpringBootApplication
@EnableFeignClients
public class MonopatinMicroserviceApplication {
	@Autowired
	private DataLoaderHelper dataLoaderHelper;
	public static void main(String[] args) {
		SpringApplication.run(MonopatinMicroserviceApplication.class, args);
	}

	@PostConstruct
	public void init() throws ParseException {
		dataLoaderHelper.loadParadas();
		dataLoaderHelper.loadMonopatines();
		dataLoaderHelper.loadViajes();
		dataLoaderHelper.loadPausas();
	}
}