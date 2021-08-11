package me.brunosantana.aws.sqs;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

//Exemplo baseado em: https://github.com/emmanuelneri-blog/aws-sqs-sample

@Component
public class SQSListener {

    @JmsListener(destination = "${sqs.queue.name}")
    public void onMessage(@Headers Map<String, Object> messageAttributes,
                                @Payload String message) {
        // Do something
        System.out.println("Messages attributes: " + messageAttributes);
        System.out.println("Body: " + message);
    }

}
