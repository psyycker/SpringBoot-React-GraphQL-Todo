package com.psyycker.graphqltodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@CrossOrigin(maxAge = 3600)
public class GraphqlTodoApplication {


	public static void main(String[] args) {
		SpringApplication.run(GraphqlTodoApplication.class, args);
	}

}
