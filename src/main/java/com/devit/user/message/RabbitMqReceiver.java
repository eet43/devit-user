package com.devit.user.message;

import com.devit.user.entity.User;
import com.devit.user.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMqReceiver implements RabbitListenerConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);

    private final UserRepository userRepository;

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }

    // 소비할 큐를 지정
    @Transactional
    @RabbitListener(queues = "${spring.rabbitmq.queue}") //유저 큐라고 가정
    public void receivedMessage(CustomMessage event) {
        logger.info("User Details Received is.. " + event);

        // message : "회원가입" 검증 로직 필요 ?

        User user = User.signUp(event.getUuid(), event.getEmail(), event.getName());
        UUID save_uuid = userRepository.save(user);
    }

}
