package cotatomanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"dao", "controller", "cotatomanage", "domain"})
public class CotatoManageApplication {

	public static void main(String[] args) {

		SpringApplication.run(CotatoManageApplication.class, args);

	}




}
