package br.com.example.broker.producer;

import br.com.example.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicProducer {

    @Value("${topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<String, StudentDto> kafkaTemplate;

    public void send(StudentDto student){
        log.info("Payload sent: {}",  student.toString());
        kafkaTemplate.send(topicName, student);
    }

}