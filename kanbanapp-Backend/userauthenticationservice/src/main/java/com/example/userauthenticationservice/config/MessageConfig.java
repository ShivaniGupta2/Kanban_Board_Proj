package com.example.userauthenticationservice.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig
{
    @Bean
    public Jackson2JsonMessageConverter consumerJackson2MessageConverter()
    {
        return new  Jackson2JsonMessageConverter();
    }

}
