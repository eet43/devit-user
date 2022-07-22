package com.devit.user.message;

import com.devit.user.dto.ResumeDto;
import com.devit.user.dto.SendResumeMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSender {
    // RabbitTemplate 클래스를 사용하면 RabbitMQ로 메시지를 보내고 받을 수 있다.
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Value("${spring.rabbitmq.board.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    public String send(ResumeDto resumeDto) {
        rabbitTemplate.convertAndSend(exchange, routingkey, resumeDto);

        return "메시지 전송 완료";
    }
}
