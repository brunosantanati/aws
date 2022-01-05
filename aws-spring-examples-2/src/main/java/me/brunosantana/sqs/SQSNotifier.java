package me.brunosantana.sqs;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.aws.messaging.core.QueueMessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SQSNotifier {

    private AmazonSQSAsync amazonSQSAsync;

    public SQSNotifier(AmazonSQSAsync amazonSQSAsync) {
        this.amazonSQSAsync = amazonSQSAsync;
    }

    public void sentToQueue(String queueName, String message){

        QueueMessageChannel messageChannel = new QueueMessageChannel(amazonSQSAsync, queueName);

        boolean send = messageChannel.send(MessageBuilder.withPayload(message).build());

        System.out.println("Enviado para a Fila - " + queueName + ": " + send);
    }

    public void sentToQueue(String queueName, Object obj){

        QueueMessageChannel messageChannel = new QueueMessageChannel(amazonSQSAsync, queueName);

        String jsonPayload;

        try {
            jsonPayload = new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            System.out.println("erro em SQSNotifier.sentToQueue " + e.getMessage());
            System.out.println("stack trace " + e);
            throw new RuntimeException(e.getMessage());
        }

        boolean send = messageChannel.send(MessageBuilder.withPayload(jsonPayload).build());

        System.out.println("Enviado para a Fila - " + queueName + ": " + send);
    }
}
