package by.dmitry_skachkov.vodarodtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VodarodTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(VodarodTestApplication.class, args);
	}

}
