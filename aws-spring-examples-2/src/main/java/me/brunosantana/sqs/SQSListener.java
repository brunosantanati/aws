package me.brunosantana.sqs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SQSListener {

    @Autowired
    private ObjectMapper mapper;

    @SqsListener("${amazon.sqs.my.queue}")
    public void onMessage(@Headers Map<String, Object> messageAttributes, @Payload String message) {

        try {
            System.out.println("SQSListener.onMessage: " + message);
            //BillingDTO messageDTO = mapper.readValue(message, BillingDTO.class);

        } catch (Exception e) {
            System.out.println("erro em SQSListener.onMessage " + e.getMessage());
            System.out.println("stack trace " + e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
