package com.uysalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.IOException;

@SpringBootApplication
@EnableCaching (proxyTargetClass=true)
public class BusMicroserviceApplication {

	public static void main(String[] args) throws IOException {


		SpringApplication.run(BusMicroserviceApplication.class, args);

	}


}
