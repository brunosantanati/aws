package me.brunosantana.aws.sqs.consumer;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.PostConstruct;

@Configuration
@EnableJms
public class SQSConfig {

    private SQSConnectionFactory connectionFactory;

    @PostConstruct
    public void init() {
        connectionFactory = createSQSConnectionFactory();
    }

    private SQSConnectionFactory createSQSConnectionFactory() {
        final AmazonSQS sqs = AmazonSQSClient.builder()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(new ProfileCredentialsProvider())
                .build();
        return new SQSConnectionFactory(new ProviderConfiguration(), sqs);
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    public JmsTemplate defaultJmsTemplate() {
        return new JmsTemplate(connectionFactory);
    }

}