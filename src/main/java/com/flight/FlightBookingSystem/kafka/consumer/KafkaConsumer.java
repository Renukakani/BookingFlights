package com.flight.FlightBookingSystem.kafka.consumer;

import com.flight.FlightBookingSystem.booking.model.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    Logger logger= LoggerFactory.getLogger(KafkaConsumer.class);
    @KafkaListener(topics = "booking-flight",groupId = "consumerFlight")//,topicPartitions = {@TopicPartition(topic="java-demo4",partitions = {"3"})})
    public void consumeEvents(String booking){

        try{
            System.out.println("_____"+booking);
        }catch(RuntimeException e){
            System.out.println("Failed to Booking");
        }

    }
}
