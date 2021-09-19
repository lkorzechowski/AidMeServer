package com.orzechowski.aidme;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity security)
    {
        try {
            security.formLogin().disable();
            //security.requiresChannel().anyRequest().requiresSecure();
            security.headers().contentTypeOptions()
                    .and().xssProtection()
                    .and().cacheControl()
                    .and().httpStrictTransportSecurity()
                    .and().frameOptions()
                    .and().permissionsPolicy()
                    .and().referrerPolicy()
                    .and().httpPublicKeyPinning()
                    .and().addHeaderWriter(
                            new StaticHeadersWriter("X-Content-Security-Policy",
                                    "script-src 'self'"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
