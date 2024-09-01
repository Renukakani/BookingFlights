package com.flight.FlightBookingSystem.kafka.producer;

import com.flight.FlightBookingSystem.booking.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,Object> template;
   // public void sendMessage(Booking message){
   public void sendMessage(String message){
        CompletableFuture<SendResult<String,Object>> future=template.send("booking-flight",message);
        future.whenComplete((result,ex)->{
            if(ex==null){
                System.out.println("Send Message=["+message+"] with offset=["+result.getRecordMetadata().offset()+"]");
            }else{
                System.out.println("Unable to send message=["+message+"] due to :"+ex.getMessage());
            }
        });
    }
}
