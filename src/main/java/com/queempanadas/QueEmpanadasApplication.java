package com.queempanadas;

import com.queempanadas.dao.jpa.H2Bootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class QueEmpanadasApplication {

	public static void main(String[] args) {
		new H2Bootstrap(null).load(true, "/initSql.sql");
		SpringApplication.run(QueEmpanadasApplication.class, args);
	}

}
