package io.wisoft.eventsourcing.nosql.query.service;

import io.wisoft.eventsourcing.nosql.query.domain.SensingDoc;
import io.wisoft.eventsourcing.nosql.query.dto.SensingMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SensingConsumerService {

    private final SensingService sensingService;

    @KafkaListener(topics = "sensing-topic1", groupId = "for-produce-sensing-group1", containerFactory = "kafkaPojoListenerContainerFactory" , id = "sensing_container1", errorHandler = "listenErrorHandler")
    public void SensingConsumer(SensingMessage message)  {
        log.info("Consumer: {} ", message);
        SensingDoc sensing = new SensingDoc();
        sensing.setSensingId(message.getSensingId());
        sensing.setSensorId(message.getSensorId());
        sensing.setSensingTime(message.getSensingTime());
        sensing.setSensingValue(message.getSensingValue());
        sensing.setEnvironmentValue(message.getEnvironmentValue());
        sensingService.saveSensing(sensing);
    }

    @Bean
    public KafkaListenerErrorHandler listenErrorHandler() {
        return (message, exception) -> {
            final StringBuilder sb = new StringBuilder();
            message.getHeaders().forEach((key, value) -> {
                sb.append(System.lineSeparator()).append(key).append(":").append(value);
            });
            log.error("listener error: {}", sb.toString());
            return "Error occured";
        };
    }
}
