package com.udea.autoevaluacion;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutoevaluacionApplication {

	public static void main(String[] args) {

		String activeProfile = System.getProperty("spring.profiles.active");
		if ("dev".equals(activeProfile)) {
			Dotenv dotenv = Dotenv.configure().filename(".env.dev").load();
			dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		}
		SpringApplication.run(AutoevaluacionApplication.class, args);
	}

}
