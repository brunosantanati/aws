package me.brunosantana.aws.sqs.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    @Autowired
    ProducerService service;

    @RequestMapping(method = RequestMethod.POST)
    public void send(@RequestBody String message) {
        service.sentToQueue(message);
    }

    @RequestMapping(value="/with-attributes", method = RequestMethod.POST)
    public void sendWithAttributes(@RequestBody String message) {
        service.sentToQueueWithAttributes(message);
    }

}
