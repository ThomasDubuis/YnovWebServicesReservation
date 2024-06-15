package com.tdubuis.reservationapp;

import com.tdubuis.reservationapp.config.SqsProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.net.URI;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
@EnableScheduling
@AllArgsConstructor
public class ReservationAppApplication {

    private final SqsProperties sqsProperties;

    public static void main(String[] args) {
        SpringApplication.run(ReservationAppApplication.class, args);
    }

    @Bean
    public SqsClient instanciateSqsClient() {
        return SqsClient.builder()
                .endpointOverride(URI.create(sqsProperties.getUrl()))
                .region(Region.EU_WEST_1)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(sqsProperties.getUsername(), sqsProperties.getPassword())))
                .build();
    }
}
