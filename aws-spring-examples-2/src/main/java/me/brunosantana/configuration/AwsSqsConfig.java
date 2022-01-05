package me.brunosantana.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableSqs
@EnableConfigurationProperties(AmazonSqsProperties.class)
public class AwsSqsConfig {

    @Autowired
    private AmazonSqsProperties amazonSqsProperties;

    @Bean
    @Primary
    public AmazonSQSAsync awsSqsClient() {
        return AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonSqsProperties.getAccessKey(), amazonSqsProperties.getSecretKey())))
                .withRegion(amazonSqsProperties.getRegion())
                .build();
    }

}