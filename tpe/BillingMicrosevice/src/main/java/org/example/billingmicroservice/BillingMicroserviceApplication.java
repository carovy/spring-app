package org.example.billingmicroservice;

import jakarta.annotation.PostConstruct;
import org.example.billingmicroservice.utils.DataLoaderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.text.ParseException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableFeignClients
public class BillingMicroserviceApplication {
	@Autowired
	private DataLoaderHelper dataLoaderHelper;
	public static void main(String[] args) {
		SpringApplication.run(BillingMicroserviceApplication.class, args);
	}
	@PostConstruct
	public void init() throws ParseException {
		dataLoaderHelper.loadBillings();
	}
}