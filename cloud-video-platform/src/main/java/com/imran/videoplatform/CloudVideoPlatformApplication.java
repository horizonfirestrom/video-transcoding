package com.imran.videoplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CloudVideoPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudVideoPlatformApplication.class, args);
	}

}
