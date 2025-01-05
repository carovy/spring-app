package org.example.usermicroservice;

import jakarta.annotation.PostConstruct;
import org.example.usermicroservice.utils.DataLoaderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;
import java.text.ParseException;

@SpringBootApplication
@EnableFeignClients
public class UserMicroserviceApplication {
	@Autowired
	private DataLoaderHelper dataLoaderHelper;

    public static void main(String[] args) {
		SpringApplication.run(UserMicroserviceApplication.class, args);
	}
	@PostConstruct
	public void init() throws ParseException {
		dataLoaderHelper.loadAccounts("UserMicrosevice/src/main/java/org/example/usermicroservice/utils/accounts.csv");
		dataLoaderHelper.loadRoles("UserMicrosevice/src/main/java/org/example/usermicroservice/utils/roles.csv");
		dataLoaderHelper.loadUsers("UserMicrosevice/src/main/java/org/example/usermicroservice/utils/users.csv");
	}
}
