package com.orzechowski.aidme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.autoconfigure.core.GcpContextAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration
@AutoConfigureAfter(GcpContextAutoConfiguration.class)
public class AidMeApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(AidMeApplication.class, args);
	}
}
