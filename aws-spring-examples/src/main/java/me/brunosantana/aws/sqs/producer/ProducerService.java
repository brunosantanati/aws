package me.brunosantana.aws.sqs.producer;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//Exemplo baseado em: https://github.com/emmanuelneri-blog/aws-sqs-sample/tree/master/producer

@Service
public class ProducerService {

    @Value("${sqs.producer.queue.name}")
    private String queueName;

    @Autowired
    private AmazonSQS amazonSQS;

    public void sentToQueue(String message) {
        final SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueName)
                .withMessageBody(message);

        amazonSQS.sendMessage(sendMessageRequest);
    }

    public void sentToQueueWithAttributes(String message) {
        final Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
        messageAttributes.put("id",
                new MessageAttributeValue()
                        .withDataType("String")
                        .withStringValue(UUID.randomUUID().toString()));

        messageAttributes.put("date",
                new MessageAttributeValue()
                        .withDataType("String")
                        .withStringValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))));

        final SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueName)
                .withMessageAttributes(messageAttributes)
                .withMessageBody(message);

        amazonSQS.sendMessage(sendMessageRequest);
    }

}
