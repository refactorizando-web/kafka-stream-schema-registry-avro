package com.refactorizando.example.schemaregistry.kafka.streams.producer;

import com.refactorizando.example.schemaregistry.kafka.streams.producer.message.avro.Car;
import com.refactorizando.example.schemaregistry.producer.message.avro.Order;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
@Configuration
public class CarProducer {


  LinkedList<Order> order = new LinkedList<>(List.of(
      new Order(UUID.randomUUID().toString(), UUID.randomUUID().toString(), createCar(),LocalDate.now()),
      new Order(UUID.randomUUID().toString(), UUID.randomUUID().toString(), createCar(),
          LocalDate.now()),
      new Order(UUID.randomUUID().toString(), UUID.randomUUID().toString(),createCar(),
          LocalDate.now()),
      new Order(UUID.randomUUID().toString(), UUID.randomUUID().toString(), createCar(),LocalDate.now())
  ));


  @Bean
  public Supplier<Message<Order>> order() {
    return () -> {
      if (order.peek() != null) {
        Message<Order> o = MessageBuilder
            .withPayload(order.peek())
            .setHeader(KafkaHeaders.MESSAGE_KEY, Objects.requireNonNull(order.poll()).getId())
            .build();
        log.info("Order event sent: {}", o.getPayload());
        return o;
      } else {
        return null;
      }
    };
  }

  private Car createCar() {

    Car car = new Car();
    car.setId("AS");
    car.setBrand("Ford");
    car.setColor("yellow");
    car.setModel("Mustang");
    return car;

  }

}
