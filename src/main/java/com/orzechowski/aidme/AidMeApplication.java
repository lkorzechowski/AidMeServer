package com.orzechowski.aidme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gcp.autoconfigure.core.GcpContextAutoConfiguration;
import org.springframework.cloud.gcp.autoconfigure.security.IapAuthenticationProperties;

@SpringBootApplication
@EnableAutoConfiguration
@ConditionalOnProperty(value = "spring.cloud.gcp.security.iap.enabled", matchIfMissing = true)
@AutoConfigureBefore(OAuth2ResourceServerAutoConfiguration.class)
@AutoConfigureAfter(GcpContextAutoConfiguration.class)
@EnableConfigurationProperties(IapAuthenticationProperties.class)
public class AidMeApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(AidMeApplication.class, args);
	}
}
