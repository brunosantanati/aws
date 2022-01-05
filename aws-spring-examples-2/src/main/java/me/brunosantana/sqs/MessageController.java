package me.brunosantana.sqs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    @Value("${amazon.sqs.my.queue}")
    private String myQueueName;

    @Autowired
    SQSNotifier notifier;

    @RequestMapping(method = RequestMethod.POST)
    public void send(@RequestBody TestDTO message) {
        notifier.sentToQueue(myQueueName, message);
    }
}
