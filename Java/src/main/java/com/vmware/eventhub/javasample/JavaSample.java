package com.vmware.eventhub.javasample;

import com.vmware.eventhub.javasample.Util.Constants;
import com.vmware.eventhub.javasample.service.RestCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootApplication
@PropertySource(value = {"classpath:application.properties" })

@EnableWebSecurity
public class JavaSample {



    public static void main(String[] args) {
        SpringApplication.run(JavaSample.class, args);
    }

    @Bean(name = "restTemplate")
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
