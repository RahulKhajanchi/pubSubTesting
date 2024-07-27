package com.pubsub.pubSubDemo.Gateway;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "pubsubOutputChannel")
public interface MessagePublishingGateway {

    public void sendToPubSub(String message);

}
