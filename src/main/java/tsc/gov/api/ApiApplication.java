package tsc.gov.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tsc.gov.api.helper.ManualLoadTest;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		try {
			ManualLoadTest.load(args);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.err.println("ManualLoadTest was interrupted: " + e.getMessage());
		}
		SpringApplication.run(ApiApplication.class, args);
	}

}
