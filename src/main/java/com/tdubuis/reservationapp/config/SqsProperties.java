package com.tdubuis.reservationapp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sqs")
@Data
public class SqsProperties {
    String url;
    String username;
    String password;
    String queueName;
    String fakeMail;
}
