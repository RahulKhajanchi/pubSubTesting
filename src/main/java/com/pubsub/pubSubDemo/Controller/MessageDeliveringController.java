package com.pubsub.pubSubDemo.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.pubsub.pubSubDemo.Gateway.MessagePublishingGateway;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Slf4j
public class MessageDeliveringController {

    private MessagePublishingGateway messagePublishingGateway;

    public MessageDeliveringController(MessagePublishingGateway messagePublishingGateway){
        this.messagePublishingGateway = messagePublishingGateway;
    }

    @PostMapping("messagePosting")
    public String postMethodName(@RequestBody String msg) {
        log.info("---- Starting to publish message ----");
        messagePublishingGateway.sendToPubSub(msg);
        log.info("---- Message Published ----");
        return "Message arrived";
    }
}
