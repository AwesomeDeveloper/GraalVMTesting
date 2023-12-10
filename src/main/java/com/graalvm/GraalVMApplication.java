package com.graalvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class,  JpaRepositoriesAutoConfiguration.class})
@ComponentScan(basePackages = "com.graalvm")
public class GraalVMApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraalVMApplication.class, args);
	}

}
