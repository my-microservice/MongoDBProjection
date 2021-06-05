package io.wisoft.eventsourcing.nosql.query.configuration;

import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.util.backoff.FixedBackOff;

import java.util.Map;

@Configuration
public class KafkaConfig {

    private final ConsumerFactory<String, String> consumerFactory;
    private final KafkaProperties kafkaProperties;

    public KafkaConfig(ConsumerFactory<String, String> consumerFactory, KafkaProperties kafkaProperties) {
        this.consumerFactory = consumerFactory;
        this.kafkaProperties = kafkaProperties;
    }


    /**
     * POJO를 Listen하는 @KafkaListener를 위한 Container Factory
     *
     * @param configurer
     * @return
     */
    @Bean
    ConcurrentKafkaListenerContainerFactory<?, ?> kafkaPojoListenerContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
        Map<String, Object> consumerProperties = this.kafkaProperties.buildConsumerProperties();
        DefaultKafkaConsumerFactory consumerFactory = new DefaultKafkaConsumerFactory(consumerProperties);
        ConcurrentKafkaListenerContainerFactory<Object, Object> listenerFactory = new ConcurrentKafkaListenerContainerFactory();
        configurer.configure(listenerFactory, consumerFactory);
        listenerFactory.setMessageConverter(new StringJsonMessageConverter()); // Kakfa로 전달된 String(JSON format)을 POJO로 변환 수행할 수 있는 메시지 컨버터를 설정
        SeekToCurrentErrorHandler seekToCurrentErrorHandler = new SeekToCurrentErrorHandler(new FixedBackOff(0L, 2L));
        listenerFactory.setErrorHandler(seekToCurrentErrorHandler);
        return listenerFactory;
    }
}
