package aulas_backend.math_projeto2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MathProjeto2Application {

	public static void main(String[] args) {
		SpringApplication.run(MathProjeto2Application.class, args);
	}

}
