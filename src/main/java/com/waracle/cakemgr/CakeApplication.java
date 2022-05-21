package com.waracle.cakemgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableJpaRepositories(basePackages="com.waracle.cakemgr.repo")

public class CakeApplication
{
    public static void main(String[] args) {
        try {
            SpringApplication.run(CakeApplication.class, "");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
