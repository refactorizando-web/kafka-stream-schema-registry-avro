package com.refactorizando.example.schemaregistry.kafka.streams.consumer;

import com.refactorizando.example.schemaregistry.producer.message.avro.Order;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class OrderConsumer {

  @Bean
  public Consumer<Order>  orders() {

    return (order) -> log.info("Consumer Received : " + order.toString());

  }

}
