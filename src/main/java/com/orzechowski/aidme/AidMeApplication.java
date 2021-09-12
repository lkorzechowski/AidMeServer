package com.orzechowski.aidme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJdbcHttpSession
public class AidMeApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(AidMeApplication.class, args);
	}
}
