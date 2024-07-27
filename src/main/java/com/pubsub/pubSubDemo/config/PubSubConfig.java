package com.pubsub.pubSubDemo.config;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Data
@ConfigurationProperties(prefix = "pubsub")
@Slf4j
public class PubSubConfig {

    private String topic;
    private String subscriber;

    @Bean
    public MessageChannel pubsubInputChannel(){
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "pubsubOutputChannel")
    public MessageHandler sendMessageToPubSub(PubSubTemplate pubSubTemplate){
        return new PubSubMessageHandler(pubSubTemplate, topic);
    }

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(
        @Qualifier("pubsubInputChannel")MessageChannel inputChannel,
        PubSubTemplate pubSubTemplate){
            PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, subscriber);
            adapter.setOutputChannel(inputChannel);
            return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "pubsubInputChannel")
    public MessageHandler messageReciever(){
        return message -> {
            log.info("---- Message arrived to the channel [{}] ----", new String((byte[]) message.getPayload()));
            BasicAcknowledgeablePubsubMessage ackMessage = message.getHeaders()
            .get(GcpPubSubHeaders.ORIGINAL_MESSAGE, BasicAcknowledgeablePubsubMessage.class);
            ackMessage.ack();
        };
    }





}
