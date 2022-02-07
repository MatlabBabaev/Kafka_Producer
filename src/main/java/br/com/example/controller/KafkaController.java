package br.com.example.controller;

import br.com.example.broker.producer.TopicProducer;
import br.com.example.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final TopicProducer topicProducer;

//    @GetMapping(value = "/send")
//    public void send(){
//        topicProducer.send("Mensagem de teste enviada ao tópico", );
//    }

    @PostMapping("/students")
    public StudentDto getStudents(@RequestBody StudentDto studentDto){
        topicProducer.send(studentDto);
        return studentDto;
    }
}
