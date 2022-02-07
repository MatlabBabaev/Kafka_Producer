package br.com.example.config;

import br.com.example.dto.StudentDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@EnableKafka
@Configuration
public class ConfigProducer {

    @Bean
    public ProducerFactory<String, StudentDto> testStateConsumerFactory(){
//        JsonDeserializer<StudentDto> deserializer = new JsonDeserializer<>(StudentDto.class);
//        deserializer.setUseTypeMapperForKey(true);

        Map<String, Object> config = Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:29092",
                //ProducerConfig.GROUP_ID_CONFIG, "Matlab",
                //ProducerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest",
                //ProducerConfig.ENABLE_AUTO_COMMIT_CONFIG, false,kafka-producer-master
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class
        );

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, StudentDto> producerTemplate(){
        return new KafkaTemplate<>(testStateConsumerFactory());
    }
}
