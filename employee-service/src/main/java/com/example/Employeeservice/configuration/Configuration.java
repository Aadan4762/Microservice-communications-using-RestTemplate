package com.example.Employeeservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
   /**
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    **/

   @Bean
   public WebClient webClient(){
       return WebClient.builder().build();
   }
}
