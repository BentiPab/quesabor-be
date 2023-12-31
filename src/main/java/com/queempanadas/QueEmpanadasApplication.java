package com.queempanadas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@PropertySource("classpath:application.yml")
public class QueEmpanadasApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueEmpanadasApplication.class, args);
	}

}
