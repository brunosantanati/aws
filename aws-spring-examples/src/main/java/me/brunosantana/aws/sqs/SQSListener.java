package me.brunosantana.aws.sqs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.brunosantana.aws.sqs.dto.Message;
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
                                @Payload String message) throws JsonProcessingException {
        // Do something
        System.out.println("Messages attributes: " + messageAttributes);
        System.out.println("Body: " + message);

        var mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(message);
        String fileName = root.at("/Records/0/s3/object/key").asText();
        String eventName = root.at("/Records/0/s3/configurationId").asText();
        System.out.println("Nome do arquivo: " + fileName);
        System.out.println("Nome do evento: " + eventName);

        Message sqsMessage = mapper.readValue(message, Message.class);
        System.out.println("- Nome do arquivo: " + sqsMessage.getRecords().get(0).getS3().getObject().getKey());
        System.out.println("- Nome do evento: " + sqsMessage.getRecords().get(0).getS3().getConfigurationId());
    }

}

/*
Exemplo de mensagem que vem na fila(relativa ao evento de criação de arquivo no bucket do S3)

{
  "Records": [
    {
      "eventVersion": "2.1",
      "eventSource": "aws:s3",
      "awsRegion": "us-east-2",
      "eventTime": "2021-08-17T14:29:53.388Z",
      "eventName": "ObjectCreated:Put",
      "userIdentity": {
        "principalId": "ASHGEXMWXSDK"
      },
      "requestParameters": {
        "sourceIPAddress": "179.145.54.75"
      },
      "responseElements": {
        "x-amz-request-id": "VZN3QV4P70H2HZ87",
        "x-amz-id-2": "D86+LqC+joLdrg7lljEtgsnB1w4+x1INEnrLEHoMZIjNjT1tAFPq/hJm9KcjFQbvIoAzKvjDvLZpTCrlCU8/3wKE1mbLUbJXERiRiObc4tE="
      },
      "s3": {
        "s3SchemaVersion": "1.0",
        "configurationId": "BrunoFileArrivalEvent",
        "bucket": {
          "name": "bruno-file-arrival",
          "ownerIdentity": {
            "principalId": "ASHGEXMWXSDK"
          },
          "arn": "arn:aws:s3:::bruno-file-arrival"
        },
        "object": {
          "key": "amazon_reviews_us_Video_Games_v1_00_short_2.tsv",
          "size": 1328,
          "eTag": "ed56f519a4cb69a45e090a530b2cfe91",
          "sequencer": "00611BC7E3CF078FEC"
        }
      }
    }
  ]
}
 */
